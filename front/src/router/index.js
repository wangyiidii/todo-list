import Vue from "vue";
import VueRouter from "vue-router";
import Login from "@/views/oauth/Login.vue";
import LoginCallback from "@/views/oauth/LoginCallback.vue";
import TodoIndex from "@/views/todo/Index.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    redirect: "/login",
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/oauth/:type/callback",
    name: "LoginCallback",
    component: LoginCallback,
  },
  {
    path: "/todo",
    name: "TodoIndex",
    component: TodoIndex,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
