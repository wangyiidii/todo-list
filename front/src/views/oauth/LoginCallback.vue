<template>
  <div class="mt-16 text-center text-2xl">登录跳转中...</div>
</template>

<script>
import { loginCallback } from "@/api/oauth";
export default {
  name: "LoginCallBack",
  data() {
    return {};
  },
  mounted() {
    let type = this.$route.params.type;
    let query = this.$route.query;
    loginCallback(type, { state: query.state, code: query.code }).then(
      (resp) => {
        console.log(resp);
        localStorage.setItem("token", resp.data.token);
        this.$router.push({ path: "/todo" });
      }
    );
  },
  methods: {},
};
</script>

<style scoped></style>
