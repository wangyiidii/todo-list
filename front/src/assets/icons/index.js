//目的是批量导入svg文件，并注册svg为全局组件，不用在单个文件再引入
import Vue from "vue";
import SvgIcon from "@/components/SvgIcon"; // svg组件
// 注册为全局组件
Vue.component("svg-icon", SvgIcon);
const req = require.context("./svg", false, /\.svg$/);
const requireAll = (requireContext) =>
  requireContext.keys().map(requireContext);
requireAll(req);
