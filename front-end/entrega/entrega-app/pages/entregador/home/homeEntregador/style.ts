import { StyleSheet } from "react-native";

export const styles = StyleSheet.create({
  screen: {
    flex: 1,
    backgroundColor: "#0B1220",
  },

  container: {
    padding: 20,
    paddingBottom: 50,
  },

heroCard: {
  backgroundColor: "#111827",
  borderRadius: 35,
  padding: 35,
  alignItems: "center",
  borderWidth: 1,
  borderColor: "#1F2937",
  boxShadow: "0px 10px 30px rgba(255, 184, 0, 0.25)", 
  elevation: 20,
},

  heroCardOnline: {
    borderColor: "#22C55E",
    shadowColor: "#22C55E",
    shadowOpacity: 0.25,
  },

  heroIcon: {
    fontSize: 70,
  },

  heroTitle: {
    color: "#FFFFFF",
    fontSize: 34,
    fontWeight: "900",
    marginTop: 15,
  },

  heroDescription: {
    color: "#94A3B8",
    fontSize: 16,
    textAlign: "center",
    lineHeight: 24,
    marginTop: 12,
  },

  heroButton: {
    marginTop: 25,
    width: "100%",
    backgroundColor: "#FBBF24", // Amarelo padrão do seu design
    borderRadius: 18,
    paddingVertical: 18,
    alignItems: "center",
  },

  // Botão fica vermelho escuro/neutro se ele quiser parar de trabalhar
  heroButtonOffline: {
    backgroundColor: "#1E293B",
    borderWidth: 1,
    borderColor: "#EF4444",
  },

  heroButtonText: {
    color: "#111827",
    fontWeight: "900",
    fontSize: 16,
  },

  heroButtonTextOffline: {
    color: "#EF4444",
  },

  sectionTitle: {
    color: "#FFFFFF",
    fontSize: 22,
    fontWeight: "800",
    marginTop: 30,
    marginBottom: 15,
  },

  features: {
    flexDirection: "row",
    justifyContent: "space-between",
  },

  featureCard: {
    width: "31%",
    backgroundColor: "#111827",
    borderRadius: 22,
    paddingVertical: 20,
    alignItems: "center",
    borderWidth: 1,
    borderColor: "#1F2937",
  },

  featureIcon: {
    fontSize: 26,
  },

  featureText: {
    color: "#FFFFFF",
    textAlign: "center",
    marginTop: 8,
    fontWeight: "800",
    fontSize: 15,
  },

  featureSubText: {
    color: "#94A3B8",
    fontSize: 12,
    marginTop: 2,
    fontWeight: "500",
  },
});