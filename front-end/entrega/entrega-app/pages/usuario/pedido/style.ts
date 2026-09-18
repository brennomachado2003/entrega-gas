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

  /* =========================
     CABEÇALHO
  ========================== */

  title: {
    fontSize: 30,
    fontWeight: "800",
    color: "#111827",
  },

  infoText: {
    fontSize: 15,
    color: "#374151",
    lineHeight: 22,
  },

  subtitle: {
    marginTop: 6,
    fontSize: 15,
    color: "#6B7280",
    lineHeight: 22,
    marginBottom: 20,
  },

  /* =========================
     CARD
  ========================== */

  card: {
    backgroundColor: "#FFFFFF",
    borderRadius: 20,
    padding: 18,
    marginBottom: 16,

    borderWidth: 1,
    borderColor: "#E5E7EB",
  },

  cardTitle: {
    fontSize: 18,
    fontWeight: "800",
    color: "#111827",
    marginBottom: 14,
  },

  /* =========================
     PEDIDO / STATUS
  ========================== */

  orderHeader: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
  },

  orderNumber: {
    fontSize: 20,
    fontWeight: "800",
    color: "#111827",
  },

  statusContainer: {
    marginTop: 14,
    paddingTop: 14,
    borderTopWidth: 1,
    borderTopColor: "#E5E7EB",

    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
  },

  statusLabel: {
    fontSize: 14,
    color: "#6B7280",
  },

  statusValue: {
    fontSize: 14,
    fontWeight: "800",
    color: "#16A34A",
  },

  /* =========================
     ENDEREÇO
  ========================== */

  addressContainer: {
    backgroundColor: "#F9FAFB",
    borderRadius: 14,
    padding: 14,
  },

  addressLabel: {
    fontSize: 12,
    fontWeight: "700",
    color: "#6B7280",
    marginBottom: 5,
  },

  addressText: {
    fontSize: 15,
    fontWeight: "600",
    color: "#111827",
    lineHeight: 22,
  },

  /* =========================
     PRODUTOS
  ========================== */

  productRow: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",

    paddingVertical: 12,

    borderBottomWidth: 1,
    borderBottomColor: "#E5E7EB",
  },

  productInfo: {
    flex: 1,
    paddingRight: 12,
  },

  productName: {
    fontSize: 15,
    fontWeight: "700",
    color: "#111827",
  },

  productQuantity: {
    marginTop: 4,
    fontSize: 13,
    color: "#6B7280",
  },

  productPrice: {
    fontSize: 15,
    fontWeight: "800",
    color: "#111827",
  },

  /* =========================
     RESUMO
  ========================== */

  row: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",

    marginBottom: 10,
  },

  label: {
    fontSize: 14,
    color: "#6B7280",
  },

  value: {
    fontSize: 14,
    fontWeight: "700",
    color: "#111827",
  },

  divider: {
    height: 1,
    backgroundColor: "#E5E7EB",
    marginVertical: 12,
  },

  totalLabel: {
    fontSize: 17,
    fontWeight: "800",
    color: "#111827",
  },

  totalValue: {
    fontSize: 20,
    fontWeight: "900",
    color: "#16A34A",
  },

  /* =========================
     ENTREGADOR
  ========================== */

  driverContainer: {
    flexDirection: "row",
    alignItems: "center",
  },

  driverAvatar: {
    width: 48,
    height: 48,
    borderRadius: 24,

    backgroundColor: "#E5E7EB",

    justifyContent: "center",
    alignItems: "center",

    marginRight: 12,
  },

  driverAvatarText: {
    fontSize: 20,
  },

  driverInfo: {
    flex: 1,
  },

  driverName: {
    fontSize: 16,
    fontWeight: "800",
    color: "#111827",
  },

  driverLabel: {
    marginTop: 3,
    fontSize: 13,
    color: "#6B7280",
  },

});

