<template>
  <a-modal
    :title="title"
    :width="800"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
      
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="factNo">
          <a-input placeholder="请输入factNo" v-decorator="['factNo', validatorRules.factNo ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="proDept">
          <a-input placeholder="请输入proDept" v-decorator="['proDept', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="customFnm">
          <a-input placeholder="请输入customFnm" v-decorator="['customFnm', validatorRules.customFnm ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="brandNm">
          <a-input placeholder="请输入brandNm" v-decorator="['brandNm', validatorRules.brandNm ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="articNo">
          <a-input placeholder="请输入articNo" v-decorator="['articNo', validatorRules.articNo ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="revOdrDate">
          <a-input placeholder="请输入revOdrDate" v-decorator="['revOdrDate', validatorRules.revOdrDate ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="custReqDate">
          <a-input placeholder="请输入custReqDate" v-decorator="['custReqDate', validatorRules.custReqDate ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="odrQty">
          <a-input placeholder="请输入odrQty" v-decorator="['odrQty', validatorRules.odrQty ]" />
        </a-form-item>
		
      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import moment from "moment"

  export default {
    name: "OrderAnalyzeModal",
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
        factNo:{rules: [{ required: true, message: '请输入factNo!' }]},
        customFnm:{rules: [{ required: true, message: '请输入customFnm!' }]},
        brandNm:{rules: [{ required: true, message: '请输入brandNm!' }]},
        articNo:{rules: [{ required: true, message: '请输入articNo!' }]},
        revOdrDate:{rules: [{ required: true, message: '请输入revOdrDate!' }]},
        custReqDate:{rules: [{ required: true, message: '请输入custReqDate!' }]},
        odrQty:{rules: [{ required: true, message: '请输入odrQty!' }]},
        },
        url: {
          add: "/order/orderAnalyze/add",
          edit: "/order/orderAnalyze/edit",
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
          this.form.setFieldsValue(pick(this.model,'factNo','proDept','customFnm','brandNm','articNo','revOdrDate','custReqDate','odrQty'))
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

</style>