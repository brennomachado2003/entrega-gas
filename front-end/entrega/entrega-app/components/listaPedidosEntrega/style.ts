import { StyleSheet } from "react-native";

export const styles = StyleSheet.create({
  container: {
    marginTop: 20,
  },

  title: {
    fontSize: 22,
    fontWeight: "800",
    color: "#FFFFFF",
    marginBottom: 16,
  },

  list: {
    gap: 12,
    paddingBottom: 20,
  },

  card: {
    backgroundColor: "#1E293B",

    borderRadius: 20,

    padding: 16,

    borderWidth: 1,
    borderColor: "#334155",
  },

  header: {
    marginBottom: 10,
  },

  pedido: {
    color: "#FFFFFF",

    fontSize: 18,

    fontWeight: "700",
  },

  address: {
    color: "#CBD5E1",

    fontSize: 14,

    marginBottom: 4,
  },

  product: {
    color: "#F8FAFC",

    fontSize: 15,

    fontWeight: "600",

    marginTop: 8,
  },

  footer: {
    flexDirection: "row",

    justifyContent: "space-between",

    marginTop: 16,
  },

  date: {
    color: "#94A3B8",
  },

  price: {
    color: "#22C55E",

    fontWeight: "800",

    fontSize: 18,
  },

  loadingContainer: {
    alignItems: "center",

    justifyContent: "center",

    padding: 30,
  },

  emptyContainer: {
    alignItems: "center",

    justifyContent: "center",

    padding: 30,
  },

  emptyText: {
    color: "#94A3B8",

    fontSize: 16,
  },
  retryButton: {
  marginTop: 16,

  backgroundColor: "#22C55E",

  paddingHorizontal: 20,

  paddingVertical: 12,

  borderRadius: 12,
},

retryButtonText: {
  color: "#FFFFFF",

  fontWeight: "700",
},

status: {
  alignSelf: "flex-start",
  fontSize: 13,
  fontWeight: "700",
  paddingHorizontal: 12,
  paddingVertical: 6,
  borderRadius: 8,
  marginBottom: 8,
},
});