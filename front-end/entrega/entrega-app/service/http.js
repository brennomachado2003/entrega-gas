const API_URL = "http://localhost:8091";

async function request(url, options = {}) {
    const response = await fetch(`${API_URL}${url}`, options);
    const contentType = response.headers.get("content-type");

    let data;
    if (contentType && contentType.includes("application/json")) {
        try {
            data = await response.json();
        } catch (e) {
            data = await response.text();
        }
    } else {
        data = await response.text();
    }

    if (!response.ok) {
        let detalheErro = "Erro na requisição";
        if (typeof data === "object" && data !== null) {
            detalheErro = data.motivo || data.mensagem || JSON.stringify(data);
        } else if (typeof data === "string" && data.trim() !== "") {
            detalheErro = data;
        }
        throw new Error(`${response.status} - ${detalheErro}`);
    }
    return data;
}

export function conectarSSE(url) {
    return new EventSource(`${API_URL}${url}`);
}
 
export function post(url, body) {
    return request(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(body)
    });
}

export function postArquivos(url, body) {
    const isFormData = body instanceof FormData;

    return request(url, {
        method: "POST",
        headers: isFormData
            ? undefined
            : { "Content-Type": "application/json" },
        body: isFormData ? body : JSON.stringify(body)
    });
}

export function get(url) {
    return request(url);
}

export function put(url, body) {
    return request(url, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(body)
    });
}

export function del(url) {
    return request(url, {
        method: "DELETE"
    });
}

export async function getBlob(url) {
    const response = await fetch(`${API_URL}${url}`, {
        method: "GET",
    });

    if (!response.ok) {
        throw new Error("Erro ao baixar o arquivo");
    }

    return await response.blob();
}

export function patch(url, body) {
    return request(url, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"
        },
        body: body ? JSON.stringify(body) : undefined
    });
}