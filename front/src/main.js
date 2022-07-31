import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "tailwindcss/tailwind.css";
import "ant-design-vue/dist/antd.css";
//引入svg
import "./assets/icons";

import {
  Button,
  Card,
  Row,
  Col,
  Collapse,
  List,
  Avatar,
  Drawer,
  FormModel,
  Form,
  Input,
  Divider,
  message,
} from "ant-design-vue";

Vue.use(Button)
  .use(Card)
  .use(Row)
  .use(Col)
  .use(Collapse)
  .use(List)
  .use(Avatar)
  .use(Drawer)
  .use(FormModel)
  .use(Form)
  .use(Input)
  .use(Divider);

Vue.config.productionTip = false;

Vue.prototype.$message = message;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
