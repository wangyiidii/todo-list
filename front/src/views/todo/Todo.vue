<template>
  <div>
    <a-row class="mt-4 p-4">
      <a-col :md="{ span: 16, offset: 4 }" :lg="{ span: 12, offset: 6 }">
        <a-card title="待办列表" class="shadow-md">
          <div slot="extra">
            <a-button
              type="primary"
              icon="plus"
              shape="circle"
              size="small"
              @click="editTodo.visible = true"
            ></a-button>
          </div>
          <a-list :data-source="todoList.todo">
            <a-list-item slot="renderItem" slot-scope="item">
              <a-list-item-meta :description="item.remark">
                <span slot="title" class="text-lg"> {{ item.content }}</span>
              </a-list-item-meta>
              <div>
                <a-button
                  type="success"
                  shape="circle"
                  icon="edit"
                  class="mx-2"
                  size="small"
                  @click="
                    showEdit({
                      id: item.id,
                      content: item.content,
                      remark: item.remark,
                    })
                  "
                />
                <a-button
                  type="success"
                  shape="circle"
                  icon="check"
                  class="mx-2"
                  size="small"
                  @click="onComplete(item.id)"
                />
                <a-button
                  type="danger"
                  shape="circle"
                  icon="close"
                  class="mx-2"
                  size="small"
                  @click="onCancel(item.id)"
                />
              </div>
            </a-list-item>
          </a-list>
        </a-card>
      </a-col>
      <a-col
        :md="{ span: 16, offset: 4 }"
        :lg="{ span: 12, offset: 6 }"
        class="mt-4"
      >
        <a-collapse>
          <a-collapse-panel key="1" header="已完成" :disabled="false">
            <a-list :data-source="todoList.completed">
              <a-list-item slot="renderItem" slot-scope="item">
                <a-list-item-meta :description="item.remark">
                  <span slot="title" class="text-lg"> {{ item.content }}</span>
                </a-list-item-meta>
                <div class="flex justify-start">
                  <div>
                    <div>完成时间</div>
                    <div>{{ item.completeTime }}</div>
                  </div>

                  <a-button
                    type="success"
                    shape="circle"
                    icon="rollback"
                    class="mx-2"
                    size="small"
                    @click="onRedo(item.id)"
                  />
                </div>
              </a-list-item>
            </a-list>
          </a-collapse-panel>
          <a-collapse-panel key="2" header="已取消" :disabled="false">
            <a-list :data-source="todoList.canceled">
              <a-list-item slot="renderItem" slot-scope="item">
                <a-list-item-meta :description="item.remark">
                  <span slot="title" class="text-lg"> {{ item.content }}</span>
                </a-list-item-meta>
                <div class="flex justify-start">
                  <div>
                    <div>取消时间</div>
                    <div>{{ item.cancelTime }}</div>
                  </div>

                  <a-button
                    type="success"
                    shape="circle"
                    icon="rollback"
                    class="mx-2"
                    size="small"
                    @click="onRedo(item.id)"
                  />
                </div>
              </a-list-item>
            </a-list>
          </a-collapse-panel>
        </a-collapse>
      </a-col>
    </a-row>

    <a-drawer
      :title="editTodo.title"
      :visible="editTodo.visible"
      @close="editTodo.visible = false"
    >
      <a-form-model :model="editTodo.form">
        <a-form-model-item label="内容">
          <a-input v-model="editTodo.form.content" type="textarea" />
        </a-form-model-item>
        <a-form-model-item label="备注">
          <a-input v-model="editTodo.form.remark" type="textarea" />
        </a-form-model-item>
      </a-form-model>

      <div class="float-right">
        <a-button size="small" @click="onSaveTodo">保存</a-button>
      </div>
    </a-drawer>
  </div>
</template>

<script>
import { list, add, update, complete, cancel, redo } from "@/api/todo";
export default {
  name: "Todo",
  data() {
    return {
      todoList: {
        todo: [],
        completed: [],
        canceled: [],
      },

      editTodo: {
        visible: false,
        title: "新增待办",
        form: {
          content: "",
          remark: "",
        },
      },
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      list({})
        .then((resp) => {
          this.todoList = { todo: [], completed: [], canceled: [] };

          resp.data.forEach((e) => {
            if (e.todoStatus == 0) {
              this.todoList.todo.push(e);
            } else if (e.todoStatus == 10) {
              this.todoList.completed.push(e);
            } else if (e.todoStatus == 20) {
              this.todoList.canceled.push(e);
            }
          });
        })
        .catch(() => {});
    },
    showEdit(todo) {
      this.editTodo.visible = true;
      this.editTodo.title = "编辑待办";
      if (todo) {
        this.editTodo.form = todo;
      }
    },
    onSaveTodo() {
      let form = this.editTodo.form;
      if (!form.content) {
        this.$message.error("请填写待办内容");
      }
      if (form.id) {
        update(form).then(() => {
          this.editTodo.visible = false;
          this.init();
        });
      } else {
        add(form).then(() => {
          this.editTodo.visible = false;
          this.init();
        });
      }

      this.editTodo.form = { content: "", remark: "" };
    },
    onComplete(id) {
      complete(id).then(() => {
        this.init();
      });
    },
    onCancel(id) {
      cancel(id).then(() => {
        this.init();
      });
    },
    onRedo(id) {
      redo(id).then(() => {
        this.init();
      });
    },
  },
};
</script>

<style scoped>
.ant-list-item {
  padding-top: 8px;
  padding-bottom: 8px;
}
</style>
