import axios from "axios";
import router from "./../router";
import { message } from "ant-design-vue";

const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API,
  timeout: 50000,
});

service.interceptors.request.use(
  (config) => {
    config.headers["token"] = localStorage.getItem("token");
    return config;
  },
  (error) => {
    console.error("interceptors request error: " + error);
    return Promise.reject(error);
  }
);

service.interceptors.response.use(
  (response) => {
    const resp = response.data;
    let msg = resp.msg;
    let code = resp.code;
    if (code != 0) {
      if (code == 40009) {
        localStorage.removeItem("token");
        setTimeout(() => {
          message.error("登录状态失效，请重新登陆");
        });
        router.push("/login");
      }
      message.error(msg);
      return Promise.reject(new Error(resp.msg || "Error"));
    } else {
      if (msg && msg.indexOf("处理成功") == -1) {
        message.success(msg);
      }
    }
    return resp;
  },
  (error) => {
    const resp = error.response.data;
    let msg = resp.msg;
    if (msg) {
      message.info(msg);
    }
    return Promise.reject(error);
  }
);

export default service;
