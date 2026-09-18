import { StyleSheet } from "react-native";

export const styles = StyleSheet.create({
  screen: {
    flex: 1,
    backgroundColor: "#EEF2F7",
  },

  container: {
    padding: 24,
    paddingBottom: 40,
  },

  title: {
    fontSize: 32,
    fontWeight: "800",
    color: "#111827",
  },

  subtitle: {
    marginTop: 8,
    fontSize: 15,
    color: "#6B7280",
    lineHeight: 22,
    marginBottom: 20,
  },

  section: {
    marginTop: 20,
  },

  sectionTitle: {
    fontSize: 18,
    fontWeight: "700",
    color: "#111827",
    marginBottom: 12,
  },

  emptyText: {
    color: "#6B7280",
    fontSize: 14,
    fontStyle: "italic",
  },

  /* =========================
     CARRINHO
  ========================== */

  cartCard: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
    backgroundColor: "#FFF",
    padding: 16,
    borderRadius: 18,
    marginBottom: 12,
    borderWidth: 1,
    borderColor: "#E5E7EB",
  },

  cartTitle: {
    fontSize: 15,
    fontWeight: "700",
    color: "#111827",
  },

  cartPrice: {
    marginTop: 4,
    fontSize: 13,
    color: "#6B7280",
  },

  cartActions: {
    flexDirection: "row",
    alignItems: "center",
    gap: 10,
  },

  qtyButton: {
    width: 34,
    height: 34,
    borderRadius: 10,
    backgroundColor: "#111827",
    justifyContent: "center",
    alignItems: "center",
  },

  qtyText: {
    color: "#FFF",
    fontSize: 18,
    fontWeight: "800",
  },

  qtyValue: {
    fontSize: 16,
    fontWeight: "700",
    color: "#111827",
  },

  /* =========================
     RESUMO
  ========================== */

  resumeCard: {
    backgroundColor: "#FFF",
    borderRadius: 20,
    padding: 18,
    marginTop: 20,
  },

  resumeTitle: {
    fontSize: 18,
    fontWeight: "800",
    marginBottom: 12,
    color: "#111827",
  },

  resumeRow: {
    flexDirection: "row",
    justifyContent: "space-between",
    marginBottom: 8,
  },

  resumeLabel: {
    color: "#6B7280",
    fontSize: 14,
  },

  resumeValue: {
    fontWeight: "700",
    color: "#111827",
  },

  divider: {
    height: 1,
    backgroundColor: "#E5E7EB",
    marginVertical: 12,
  },

  totalLabel: {
    fontSize: 16,
    fontWeight: "800",
    color: "#111827",
  },

  totalValue: {
    fontSize: 18,
    fontWeight: "900",
    color: "#16A34A",
  },

  /* =========================
     AÇÕES
  ========================== */

  actions: {
    flexDirection: "row",
    gap: 12,
    marginTop: 20,
  },

  clearButton: {
    flex: 1,
    backgroundColor: "#EF4444",
    paddingVertical: 14,
    borderRadius: 16,
    alignItems: "center",
  },

  clearButtonText: {
    color: "#FFF",
    fontSize: 14,
    fontWeight: "800",
  },

  confirmButton: {
    flex: 2,
    backgroundColor: "#16A34A",
    paddingVertical: 14,
    borderRadius: 16,
    alignItems: "center",
  },

  confirmButtonText: {
    color: "#FFF",
    fontSize: 14,
    fontWeight: "800",
  },
});