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
          label="订单编号">
          <a-input placeholder="请输入订单编号" v-decorator="['订单编号', validatorRules.订单编号 ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="型体">
          <a-input placeholder="请输入型体" v-decorator="['型体', validatorRules.型体 ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="颜色">
          <a-input placeholder="请输入颜色" v-decorator="['颜色', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="接单日期">
          <a-input placeholder="请输入接单日期" v-decorator="['接单日期', validatorRules.接单日期 ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="客户交期">
          <a-input placeholder="请输入客户交期" v-decorator="['客户交期', validatorRules.客户交期 ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="size编号">
          <a-input placeholder="请输入size编号" v-decorator="['size编号', validatorRules.size编号 ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="size序号">
          <a-input placeholder="请输入size序号" v-decorator="['size序号', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="订单数">
          <a-input placeholder="请输入订单数" v-decorator="['订单数', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="生产数">
          <a-input placeholder="请输入生产数" v-decorator="['生产数', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="入库数">
          <a-input placeholder="请输入入库数" v-decorator="['入库数', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="调整数">
          <a-input placeholder="请输入调整数" v-decorator="['调整数', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="库存数">
          <a-input placeholder="请输入库存数" v-decorator="['库存数', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="欠数">
          <a-input placeholder="请输入欠数" v-decorator="['欠数', {}]" />
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
    name: "EndProductDemandModal",
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
        订单编号:{rules: [{ required: true, message: '请输入订单编号!' }]},
        型体:{rules: [{ required: true, message: '请输入型体!' }]},
        接单日期:{rules: [{ required: true, message: '请输入接单日期!' }]},
        客户交期:{rules: [{ required: true, message: '请输入客户交期!' }]},
        size编号:{rules: [{ required: true, message: '请输入size编号!' }]},
        },
        url: {
          add: "/stock/endProductDemand/add",
          edit: "/stock/endProductDemand/edit",
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
          this.form.setFieldsValue(pick(this.model,'factNo','proDept','订单编号','型体','颜色','接单日期','客户交期','size编号','size序号','订单数','生产数','入库数','调整数','库存数','欠数'))
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