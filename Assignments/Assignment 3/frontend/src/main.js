import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import vuetify from "./plugins/vuetify";
import "material-design-icons-iconfont/dist/material-design-icons.css";
import "./api";
import doctor_appointments from "./store";

Vue.config.productionTip = false;

new Vue({
  router,
  vuetify,
  doctor_appointments,
  render: (h) => h(App),
}).$mount("#app");
