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

  header: {
    marginTop: 30,
    marginBottom: 25,

    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
  },

  location: {
    color: "#FBBF24",
    fontSize: 14,
    fontWeight: "700",
  },

  greeting: {
    color: "#FFFFFF",
    fontSize: 34,
    fontWeight: "800",
    marginTop: 8,
  },

  subtitle: {
    color: "#94A3B8",
    marginTop: 10,
    fontSize: 16,
    lineHeight: 24,
  },

  profile: {
    width: 58,
    height: 58,

    borderRadius: 29,

    backgroundColor: "#1E293B",

    justifyContent: "center",
    alignItems: "center",

    borderWidth: 2,
    borderColor: "#334155",
  },

  profileText: {
    color: "#FFFFFF",
    fontWeight: "800",
    fontSize: 18,
  },

  heroCard: {
    backgroundColor: "#111827",

    borderRadius: 35,

    padding: 35,

    alignItems: "center",

    borderWidth: 1,
    borderColor: "#1F2937",

    shadowColor: "#FFB800",

    shadowOffset: {
      width: 0,
      height: 10,
    },

    shadowOpacity: 0.25,

    shadowRadius: 30,

    elevation: 20,
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

    backgroundColor: "#FBBF24",

    borderRadius: 18,

    paddingVertical: 18,

    alignItems: "center",
  },

  heroButtonText: {
    color: "#111827",

    fontWeight: "900",

    fontSize: 16,
  },

  activityCard: {
    marginTop: 20,

    backgroundColor: "#12203A",

    borderRadius: 24,

    padding: 20,

    borderLeftWidth: 5,

    borderLeftColor: "#22C55E",
  },

  activityTitle: {
    color: "#22C55E",

    fontWeight: "800",

    marginBottom: 8,

    fontSize: 16,
  },

  activityText: {
    color: "#CBD5E1",

    lineHeight: 22,
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

    paddingVertical: 25,

    alignItems: "center",

    borderWidth: 1,
    borderColor: "#1F2937",
  },

  featureIcon: {
    fontSize: 30,
  },

  featureText: {
    color: "#FFFFFF",

    textAlign: "center",

    marginTop: 10,

    fontWeight: "700",
  },

  lastOrder: {
    backgroundColor: "#111827",

    borderRadius: 25,

    padding: 22,

    borderWidth: 1,
    borderColor: "#1F2937",
  },

  lastOrderTitle: {
    color: "#FFFFFF",

    fontSize: 18,

    fontWeight: "800",
  },

  lastOrderStatus: {
    color: "#22C55E",

    marginTop: 10,

    fontWeight: "700",
  },

  lastOrderDate: {
    color: "#94A3B8",

    marginTop: 10,
  },
});