<template>
  <v-app>
    <ToolBar style="z-index: 1"></ToolBar>
    <SideNav></SideNav>
  </v-app>
</template>

<script>
import SideNav from "./components/SideNav"
import ToolBar from "./components/ToolBar"
export default {
  name: "App",
  components: {
    SideNav,
    ToolBar
  },

  data: () => ({})
}
</script>
