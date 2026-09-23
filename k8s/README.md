# Como rodar o Uber Gas no Kubernetes

Este projeto utiliza Kubernetes para orquestrar os microsserviços, o frontend e o Eureka.

O PostgreSQL/PostGIS, Kafka e Graylog ficam fora do Kubernetes e continuam sendo executados separadamente, por exemplo, através do Docker Compose.

A estrutura dos manifests é:

```text
k8s/
│
├── 00-namespace.yaml
├── 01-server.yaml
├── 02-empresa.yaml
├── 03-entregador.yaml
├── 04-estoque.yaml
├── 05-gateway.yaml
├── 06-logistica.yaml
├── 07-notificacao.yaml
├── 08-pedidos.yaml
├── 09-produtos.yaml
├── 10-usuario.yaml
└── 11-frontend.yaml
```

---

## 1. Instalar as ferramentas

No macOS, instale o `kubectl` e o `kind`:

```bash
brew install kubectl kind
```

Também é necessário ter o Docker instalado e em execução.

Verifique:

```bash
docker --version
kubectl version --client
kind version
```

---

## 2. Criar o cluster Kubernetes

Crie o cluster chamado `ecommerce`:

```bash
kind create cluster --name ecommerce
```

Verifique:

```bash
kubectl cluster-info
```

E:

```bash
kubectl get nodes
```

Deve aparecer aproximadamente:

```text
NAME                     STATUS   ROLES
ecommerce-control-plane  Ready    control-plane
```

> O nome `ecommerce` é apenas o nome do cluster Kind. O namespace utilizado pelos manifests também é `ecommerce`, conforme definido no `00-namespace.yaml`.

---

## 3. Criar as imagens Docker

As imagens dos microsserviços precisam existir antes de serem utilizadas pelo Kubernetes.

Na raiz do projeto `Uber Gas`, execute os builds:

```bash
docker build -t server:1.0 ./back-end/server
docker build -t empresa:1.0 ./back-end/empresa
docker build -t entregador:1.0 ./back-end/entregador
docker build -t estoque:1.0 ./back-end/estoque
docker build -t logistica:1.0 ./back-end/logistica
docker build -t notificacao:1.0 ./back-end/notificacao
docker build -t pedidos:1.0 ./back-end/pedidos
docker build -t produtos:1.0 ./back-end/produtos
docker build -t usuario:1.0 ./back-end/usuario
docker build -t gateway:1.0 ./back-end/gateway
docker build -t frontend:1.0 ./front-end/entrega/entrega-app
```

### Importante

Não é necessário criar imagens Docker para:

```text
PostgreSQL/PostGIS
Kafka
Graylog
```

Esses componentes não fazem parte dos manifests Kubernetes.

Eles continuam sendo executados externamente ao cluster Kubernetes.

---

## 4. Carregar as imagens no Kind

Como o cluster foi criado pelo Kind, carregue as imagens locais para dentro do cluster:

```bash
kind load docker-image server:1.0 --name ecommerce
kind load docker-image empresa:1.0 --name ecommerce
kind load docker-image entregador:1.0 --name ecommerce
kind load docker-image estoque:1.0 --name ecommerce
kind load docker-image logistica:1.0 --name ecommerce
kind load docker-image notificacao:1.0 --name ecommerce
kind load docker-image pedidos:1.0 --name ecommerce
kind load docker-image produtos:1.0 --name ecommerce
kind load docker-image usuario:1.0 --name ecommerce
kind load docker-image gateway:1.0 --name ecommerce
kind load docker-image frontend:1.0 --name ecommerce
```

---

## 5. Aplicar os manifests

Na raiz do projeto:

```bash
kubectl apply -f k8s/
```

Os manifests são:

```text
00-namespace.yaml
01-server.yaml
02-empresa.yaml
03-entregador.yaml
04-estoque.yaml
05-gateway.yaml
06-logistica.yaml
07-notificacao.yaml
08-pedidos.yaml
09-produtos.yaml
10-usuario.yaml
11-frontend.yaml
```

Você também pode aplicar individualmente, caso queira testar cada componente:

```bash
kubectl apply -f k8s/00-namespace.yaml
kubectl apply -f k8s/01-server.yaml
kubectl apply -f k8s/02-empresa.yaml
```

E assim por diante.

---

## 6. Verificar os Pods

Execute:

```bash
kubectl get pods -n ecommerce
```

Para acompanhar em tempo real:

```bash
kubectl get pods -n ecommerce -w
```

Quando terminar, pressione:

```text
Ctrl + C
```

Para uma visão mais completa:

```bash
kubectl get all -n ecommerce
```

---

## 7. Verificar os Services

Execute:

```bash
kubectl get services -n ecommerce
```

Você deverá encontrar Services semelhantes a:

```text
server
empresa
entregador
estoque
gateway
logistica
notificacao
pedidos
produtos
usuario
frontend
```

PostgreSQL, Kafka e Graylog não aparecerão nessa lista, pois estão fora do Kubernetes.

---

# Comunicação interna

Dentro do Kubernetes, os microsserviços utilizam o nome do Service como hostname.

### Eureka

Os microsserviços devem utilizar:

```text
http://server:8088/eureka
```

### PostgreSQL

O PostgreSQL está fora do Kubernetes.

Os microsserviços precisam utilizar o endereço onde o PostgreSQL estiver disponível.

Exemplo:

```text
jdbc:postgresql://<ENDERECO-DO-POSTGRES>:5432/empresa
```

ou:

```text
jdbc:postgresql://<ENDERECO-DO-POSTGRES>:5432/pedidos
```

Os bancos utilizados pelo projeto são:

```text
empresa
entregador
estoque
logistica
notificacao
pedidos
produtos
usuario
```

> O endereço `<ENDERECO-DO-POSTGRES>` deve ser substituído pelo endereço realmente acessível pelos Pods do Kubernetes.

### Kafka

O Kafka também está fora do Kubernetes.

Os microsserviços devem utilizar o endereço do Kafka que seja acessível pelo cluster.

Exemplo:

```text
<ENDERECO-DO-KAFKA>:19092
```

Caso `kafka-1` e `kafka-2` sejam acessíveis a partir do Kubernetes:

```text
kafka-1:19092,kafka-2:19092
```

> Não utilize `kafka-3`, pois o projeto possui apenas dois brokers.

### Graylog

O Graylog também está fora do Kubernetes.

Configure os serviços que enviam logs para utilizar o endereço correspondente ao ambiente externo.

Exemplo:

```text
GRAYLOG_HOST=<ENDERECO-DO-GRAYLOG>
GRAYLOG_PORT=12201
```

---

# 8. Acessar o Gateway

Como o cluster Kind é local, você pode criar um túnel para o Gateway:

```bash
kubectl port-forward -n ecommerce svc/gateway 8091:8091
```

Agora o Gateway poderá ser acessado pelo computador em:

```text
http://localhost:8091
```

Mantenha o terminal executando o `port-forward` enquanto estiver utilizando o Gateway.

---

# 9. Acessar o Frontend

O frontend possui um Service próprio.

Verifique:

```bash
kubectl get service frontend -n ecommerce
```

Para acessar localmente:

```bash
kubectl port-forward -n ecommerce svc/frontend 3000:80
```

Depois abra:

```text
http://localhost:3000
```

---

# 10. Acessar o Eureka

O Eureka está no Service:

```text
server
```

Faça o port-forward:

```bash
kubectl port-forward -n ecommerce svc/server 8088:8088
```

Depois acesse:

```text
http://localhost:8088
```

---

# 11. Verificar os microsserviços

Para verificar todos os Pods:

```bash
kubectl get pods -n ecommerce
```

Para verificar um serviço específico:

```bash
kubectl get pods -n ecommerce -l app=produtos
```

Para visualizar os logs:

```bash
kubectl logs -n ecommerce deployment/produtos
```

Exemplos:

```bash
kubectl logs -n ecommerce deployment/empresa
kubectl logs -n ecommerce deployment/pedidos
kubectl logs -n ecommerce deployment/usuario
```

---

# 12. Diagnóstico de problemas

Se algum Pod estiver em:

```text
0/1
```

verifique:

```bash
kubectl get pods -n ecommerce
```

Depois veja os detalhes:

```bash
kubectl describe pod <nome-do-pod> -n ecommerce
```

E os logs:

```bash
kubectl logs <nome-do-pod> -n ecommerce
```

Para acompanhar os logs de um Deployment:

```bash
kubectl logs -n ecommerce deployment/produtos
```

Para verificar os eventos:

```bash
kubectl get events -n ecommerce
```

---

# 13. Verificar as portas dos microsserviços

Se algum serviço estiver com erro de readiness, verifique os logs:

```bash
kubectl logs -n ecommerce deployment/produtos
```

Confira se o Spring Boot iniciou na porta configurada.

As portas dos componentes Kubernetes são:

```text
produtos       8082
empresa        8083
logistica      8084
usuario        8085
entregador     8086
estoque        8087
server         8088
pedidos        8089
notificacao    8090
gateway        8091
frontend       80
```

As portas dos componentes externos são gerenciadas fora do Kubernetes.

```text
PostgreSQL     5432
Kafka          19092
Graylog        12201
```

---

# 14. Alterei o código de um microsserviço

Por exemplo, se você alterou o serviço `produtos`:

### 1. Gere a nova imagem

```bash
docker build -t produtos:1.0 ./back-end/produtos
```

### 2. Carregue a imagem no Kind

```bash
kind load docker-image produtos:1.0 --name ecommerce
```

### 3. Reinicie o Deployment

```bash
kubectl rollout restart deployment/produtos -n ecommerce
```

### 4. Verifique

```bash
kubectl rollout status deployment/produtos -n ecommerce
```

---

# 15. Escalar um microsserviço

Como o Kubernetes é responsável pela escalabilidade, um microsserviço stateless pode ter múltiplas réplicas.

Por exemplo:

```bash
kubectl scale deployment empresa --replicas=3 -n ecommerce
```

Verifique:

```bash
kubectl get pods -n ecommerce
```

Você verá três Pods do serviço `empresa`.

Para voltar para uma réplica:

```bash
kubectl scale deployment empresa --replicas=1 -n ecommerce
```

---

# 16. Ver todos os recursos

```bash
kubectl get all -n ecommerce
```

Para verificar os Deployments:

```bash
kubectl get deployments -n ecommerce
```

Para verificar os Services:

```bash
kubectl get services -n ecommerce
```

Para verificar os Pods:

```bash
kubectl get pods -n ecommerce
```

---

# 17. Derrubar o ambiente

Para remover todos os recursos do Kubernetes:

```bash
kubectl delete -f k8s/
```

Se quiser remover completamente o cluster Kind:

```bash
kind delete cluster --name ecommerce
```

Isso remove o cluster local e todos os recursos que estão dentro dele.

---

# Resumo da arquitetura

O Kubernetes ficará responsável pela aplicação:

```text
                    Kubernetes
                         │
        ┌────────────────┼────────────────┐
        │                │                │
        ▼                ▼                ▼
    Frontend          Gateway          Eureka
      :80              :8091            :8088
                         │
          ┌──────────────┼──────────────┐
          │              │              │
          ▼              ▼              ▼
       Empresa       Entregador       Estoque
       :8083           :8086           :8087
          │              │              │
          ├──────────────┼──────────────┤
          │              │              │
          ▼              ▼              ▼
      Logistica      Notificacao       Pedidos
        :8084           :8090           :8089
          │              │              │
          └──────────────┼──────────────┘
                         │
                  ┌──────┴──────┐
                  ▼             ▼
              Produtos       Usuario
                :8082          :8085


              Infraestrutura externa
                       │
          ┌────────────┼────────────┐
          ▼            ▼            ▼
      PostgreSQL      Kafka       Graylog
       /PostGIS
```

## Componentes Kubernetes

| Arquivo | Componente | Tipo |
|---|---|---|
| `00-namespace.yaml` | Namespace | Namespace |
| `01-server.yaml` | Eureka | Deployment + Service |
| `02-empresa.yaml` | Empresa | Deployment + Service |
| `03-entregador.yaml` | Entregador | Deployment + Service |
| `04-estoque.yaml` | Estoque | Deployment + Service |
| `05-gateway.yaml` | Gateway | Deployment + Service |
| `06-logistica.yaml` | Logística | Deployment + Service |
| `07-notificacao.yaml` | Notificação | Deployment + Service |
| `08-pedidos.yaml` | Pedidos | Deployment + Service |
| `09-produtos.yaml` | Produtos | Deployment + Service |
| `10-usuario.yaml` | Usuário | Deployment + Service |
| `11-frontend.yaml` | Frontend | Deployment + Service |

## Componentes externos ao Kubernetes

| Componente | Responsabilidade |
|---|---|
| PostgreSQL/PostGIS | Banco de dados |
| Kafka | Comunicação assíncrona e eventos |
| Graylog | Centralização de logs |