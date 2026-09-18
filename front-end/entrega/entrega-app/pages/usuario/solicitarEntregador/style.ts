import { StyleSheet } from "react-native";

export const styles = StyleSheet.create({
  screen: {
    flex: 1,
    backgroundColor: "#0B1220",
  },

  container: {
    padding: 20,
    paddingBottom: 40,
    justifyContent: "center",
    flexGrow: 1,
  },

  card: {
    backgroundColor: "#111827",
    borderRadius: 30,
    padding: 35,
    alignItems: "center",
    borderWidth: 1,
    borderColor: "#1F2937",
  },

  icon: {
    fontSize: 80,
  },

  title: {
    marginTop: 20,
    color: "#FFFFFF",
    fontSize: 30,
    fontWeight: "900",
  },

  description: {
    marginTop: 15,
    color: "#94A3B8",
    textAlign: "center",
    lineHeight: 24,
    fontSize: 16,
  },

  loading: {
    marginTop: 35,
  },

  status: {
    marginTop: 20,
    color: "#22C55E",
    fontWeight: "700",
    fontSize: 16,
  },

  error: {
    marginTop: 20,
    color: "#EF4444",
    fontWeight: "700",
    fontSize: 16,
  },

  infoCard: {
    marginTop: 30,
    backgroundColor: "#111827",
    borderRadius: 25,
    padding: 25,
    borderWidth: 1,
    borderColor: "#1F2937",
  },

  infoTitle: {
    color: "#FBBF24",
    fontWeight: "800",
    fontSize: 18,
  },

  infoText: {
    marginTop: 10,
    color: "#FFFFFF",
    fontSize: 24,
    fontWeight: "800",
  },

  cancelButton: {
    marginTop: 30,
    backgroundColor: "#DC2626",
    borderRadius: 18,
    paddingVertical: 18,
    alignItems: "center",
  },

  cancelButtonText: {
    color: "#FFFFFF",
    fontSize: 16,
    fontWeight: "900",
  },
});