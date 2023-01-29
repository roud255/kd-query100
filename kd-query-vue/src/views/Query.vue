<template>
  <div class="common-layout">
    <el-container>
      <el-header style="padding: 0; margin: 0">
        <div style="height: 60px; background: #6c8fce; padding-left: 15%">
          <h3 style="height:60px; line-height:60px; color: white; font-size: 26px; font-weight: 500">快递侠</h3>
        </div>
      </el-header>
      <el-main style="height: calc(100vh - 120px);">
        <div class="user_input">
          <div style="position:relative; width: 70%;margin-left: 15%; margin-bottom: 30px;margin-top: 30px">
            <el-input
                v-model="kuaidinum"
                class="w-50 m-2 selfinput"
                size="mini"
                placeholder="输入单号"
                @keyup.enter = "query"
            />
            <el-button type="primary" class="self_btn" :icon="Search" @click="query">查询</el-button>
          </div>
          <div style="width: 70%;margin-left: 15%; margin-bottom: 30px">
            <el-select v-model="kuaidicom" class="m-2" placeholder="选择快递公司">
              <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              />
            </el-select>
          </div>
          <div style="width: 70%;margin-left: 15%; margin-bottom: 30px;margin-top: 30px">
            <el-input v-model="phone" placeholder="收寄件人电话号码（顺丰必填）" />
          </div>
        </div>
        <div class="trackBox" style="width: 70%; margin-left: 15%; height: 400px; overflow: auto">
          <el-timeline>
            <el-timeline-item
                v-for="(item,index) in trackData"
                :key="index"
                :icon="item.icon"
                :color="item.color"
                :timestamp="item.timestamp"
            >
              {{ item.content }}
            </el-timeline-item>
          </el-timeline>
        </div>
      </el-main>
      <el-footer style="height: 60px">
        <p style="height: 60px; line-height:60px; text-align: center; font-weight: 300; font-size: 12px">©2023 http://www.roud.top</p>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import request from "../utils/request";
import {ElMessage} from "element-plus";

export default {
  name: "Query",
  data(){
    return{
      options : [
        {
          value: 'zhongtong',
          label: '中通快递',
        },
        {
          value: 'jtexpress',
          label: '极兔速递',
        },
        {
          value: 'yunatong',
          label: '圆通速递',
        },
        {
          value: 'shunfeng',
          label: '顺丰速运',
        },
        {
          value: 'yunda',
          label: '韵达快递',
        },
        {
          value: 'shentong',
          label: '申通快递',
        },
        {
          value: 'youzhengguonei',
          label: '邮政速递',
        },{
          value: 'ems',
          label: 'EMS',
        },
        {
          value: 'jd',
          label: '京东物流',
        },
        {
          value: 'debangkuaidi',
          label: '德邦快递',
        },
        {
          value: 'debang物流',
          label: '德邦物流',
        },
        {
          value: 'fengwang',
          label: '丰网速运',
        },
        {
          value: 'sxjdfreight',
          label: '顺心捷达',
        },
        {
          value: 'zhaijisong',
          label: '宅急送',
        },
        {
          value: 'danniao',
          label: '丹鸟',
        },
        {
          value: 'shunfengkuaiyun',
          label: '顺丰快运',
        },
        {
          value: 'shunfengkuaiyun',
          label: '顺丰快运',
        },
        {
          value: 'disifang',
          label: '递四方',
        },{
          value: 'zhuanyunsifang',
          label: '转运四方',
        },{
          value: 'ups',
          label: 'UPS',
        },{
          value: 'jinguangsudikuaijian',
          label: '京广速递',
        },
        {
          value: 'yimidida',
          label: '壹米滴答',
        },
        {
          value: 'shunfengkuaiyun',
          label: '顺丰快运',
        }
      ],
      kuaidinum: "",
      kuaidicom: "",
      phone: "",
      trackData: [],
      form: {}
    }
  },
  methods:{
    showSuccessMessage(msg){
      ElMessage.success({
        message: msg,
      });
    },
    showFailMessage(msg){
      ElMessage.error({
        message: msg,
      });
    },
    showWarningMessage(msg){
      ElMessage.warning({
        message: msg,
      });
    },
    query(){
      this.form = {}
      this.trackData = []
      if(this.kuaidinum === "" || this.kuaidinum == null || this.kuaidicom == null || this.kuaidicom === ""){
        this.showWarningMessage("请输入准确的信息！");
        return;
      }
      var reg = /^[a-zA-Z]*[0-9]+$/
      if(!reg.test(this.kuaidinum)){
        this.showWarningMessage("请输入正确的单号！");
        return;
      }
      this.form.num = this.kuaidinum;
      this.form.com = this.kuaidicom;
      if("shunfeng" === this.kuaidicom){
        this.form.phone = this.phone;
      }
      request.post("/query/kd100", this.form).then(res => {
        let data = res["data"];
        if((data[0])["code"] === 101){
          (data[0])["color"] = "green";
        }
        if(data.length > 1 && (data[data.length-1])["code"] === 101){
          (data[data.length-1])["icon"] = "ArrowDownBold"
        }
        if((data[0])["code"] === 101 || (data[0])["code"] === 102){
          this.showSuccessMessage("查询成功");
        }else {
          this.showWarningMessage("查询异常")
        }
        this.trackData = data.reverse();
      });
    }
  }
}
</script>

<style>
  *{
    padding: 0;
    margin: 0;
  }
  .el-select{
    width: 100%;
  }
  .selfinput{
    width: 90% !important;
  }
  .self_btn{
    position: absolute;
    right: 0 !important;
  }

  ::-webkit-scrollbar {
    height: 6px;
    width: 3px;
  }
  ::-webkit-scrollbar-track {
    background-color: transparent;
  }
  ::-webkit-scrollbar-thumb {

    box-shadow: inset 0 0 0 rgba(240, 240, 240, .5);
    border-radius: 2px;
    background-color: rgba(240, 240, 240, .5);
  }
</style>