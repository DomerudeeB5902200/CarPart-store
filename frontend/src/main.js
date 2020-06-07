import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import vuetify from "@/plugins/vuetify";
import CarPartService from "./services/car-part.service";
Vue.config.productionTip = false;
Vue.use(new CarPartService());
new Vue({
  router,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
