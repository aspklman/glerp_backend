<template>
  <a-drawer
      :title="title"
      :width="800"
      placement="right"
      :closable="false"
      @close="close"
      :visible="visible"
  >

    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
      
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="厂区编号">
          <a-input placeholder="请输入厂区编号" v-decorator="['factNo', validatorRules.factNo ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="阶段编号">
          <a-input placeholder="请输入阶段编号" v-decorator="['stgNo', validatorRules.stgNo ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="条码编号">
          <a-input placeholder="请输入条码编号" v-decorator="['barNo', validatorRules.barNo ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="接收日期">
          <a-input placeholder="请输入接收日期" v-decorator="['recDate', validatorRules.recDate ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="接收时间">
          <a-input placeholder="请输入接收时间" v-decorator="['recTime', validatorRules.recTime ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="组别编号">
          <a-input placeholder="请输入组别编号" v-decorator="['secNo', validatorRules.secNo ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="入库单号">
          <a-input placeholder="请输入入库单号" v-decorator="['inNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="库别">
          <a-input placeholder="请输入库别" v-decorator="['stkNo', validatorRules.stkNo ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="存放区编号">
          <a-input placeholder="请输入存放区编号" v-decorator="['locateNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="电脑IP">
          <a-input placeholder="请输入电脑IP" v-decorator="['scanIp', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="生产日期">
          <a-input placeholder="请输入生产日期" v-decorator="['proDate', validatorRules.proDate ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="异动人">
          <a-input placeholder="请输入异动人" v-decorator="['userNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="生产厂别">
          <a-input placeholder="请输入生产厂别" v-decorator="['proFact', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="产生入库单标记(0:未产生 1:已产生 2:待产生)">
          <a-input placeholder="请输入产生入库单标记(0:未产生 1:已产生 2:待产生)" v-decorator="['genInStockRmk', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="存放地点">
          <a-input placeholder="请输入存放地点" v-decorator="['locArea', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="locNoOut">
          <a-input placeholder="请输入locNoOut" v-decorator="['locNoOut', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="locAreaOut">
          <a-input placeholder="请输入locAreaOut" v-decorator="['locAreaOut', {}]" />
        </a-form-item>
		
      </a-form>
    </a-spin>
    <a-button type="primary" @click="handleOk">确定</a-button>
    <a-button type="primary" @click="handleCancel">取消</a-button>
  </a-drawer>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from "moment"

  export default {
    name: "StgscanRecModal",
    data () {
      return {
        title:"操作",
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },

        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules:{
        factNo:{rules: [{ required: true, message: '请输入厂区编号!' }]},
        stgNo:{rules: [{ required: true, message: '请输入阶段编号!' }]},
        barNo:{rules: [{ required: true, message: '请输入条码编号!' }]},
        recDate:{rules: [{ required: true, message: '请输入接收日期!' }]},
        recTime:{rules: [{ required: true, message: '请输入接收时间!' }]},
        secNo:{rules: [{ required: true, message: '请输入组别编号!' }]},
        stkNo:{rules: [{ required: true, message: '请输入库别!' }]},
        proDate:{rules: [{ required: true, message: '请输入生产日期!' }]},
        },
        url: {
          add: "/scan/stgscanRec/add",
          edit: "/scan/stgscanRec/edit",
        },
      }
    },
    created () {
    },
    methods: {
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'factNo','stgNo','barNo','recDate','recTime','secNo','inNo','stkNo','locateNo','scanIp','proDate','userNo','proFact','genInStockRmk','locArea','locNoOut','locAreaOut'))
		  //时间格式化
        });

      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            //时间格式化
            
            console.log(formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })



          }
        })
      },
      handleCancel () {
        this.close()
      },


    }
  }
</script>

<style lang="less" scoped>
/** Button按钮间距 */
  .ant-btn {
    margin-left: 30px;
    margin-bottom: 30px;
    float: right;
  }
</style>