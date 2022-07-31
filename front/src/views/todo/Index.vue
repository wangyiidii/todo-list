<template>
  <div>
    <todo></todo>
  </div>
</template>

<script>
import todo from "@/views/todo/Todo";

export default {
  name: "TodoIndex",
  components: { todo },
  data() {
    return {};
  },
};
</script>

<style scoped></style>
