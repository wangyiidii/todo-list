import request from "@/util/request";

export function list(params) {
  return request({
    url: "/todo",
    method: "get",
    params,
  });
}

export function add(data) {
  return request({
    url: "/todo",
    method: "post",
    data,
  });
}

export function update(data) {
  return request({
    url: "/todo",
    method: "put",
    data,
  });
}

export function complete(id) {
  return request({
    url: `/todo/${id}/complete`,
    method: "put",
  });
}

export function cancel(id) {
  return request({
    url: `/todo/${id}/cancel`,
    method: "put",
  });
}

export function redo(id) {
  return request({
    url: `/todo/${id}/redo`,
    method: "put",
  });
}
