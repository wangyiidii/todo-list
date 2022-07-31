<template>
  <div>
    <a-row class="mt-4 p-4">
      <a-col :md="{ span: 16, offset: 4 }" :lg="{ span: 12, offset: 6 }">
        <a-card title="点击授权以登录">
          <div class="text-center">
            <div class="text-4xl inline-block w-12">
              <svg-icon icon-class="gitee" @click="Login('GITEE')" />
              <div class="text-base font-bold mt-2">Gitee</div>
            </div>
            <!--            <div class="text-4xl inline-block w-12 ml-8">-->
            <!--              <svg-icon icon-class="gitee" />-->
            <!--              <div class="text-base">Gitee</div>-->
            <!--            </div>-->
          </div>
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {};
  },
  mounted() {
    if (localStorage.getItem("token")) {
      this.$router.push("/todo");
    }
  },
  methods: {
    Login(type) {
      // this.$router.push({
      //   path: process.env.VUE_APP_BASE_API + "/oauth/login/" + type,
      // });

      window.location.href =
        process.env.VUE_APP_BASE_API + "/oauth/login/" + type;
    },
  },
};
</script>

<style scoped></style>
