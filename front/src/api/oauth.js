import request from "@/util/request";

export function Login(type) {
  return request({
    url: "/oauth/login/" + type,
    method: "get",
  });
}

export function loginCallback(type, data) {
  return request({
    url: `/oauth/${type}/callback`,
    method: "post",
    data,
  });
}
