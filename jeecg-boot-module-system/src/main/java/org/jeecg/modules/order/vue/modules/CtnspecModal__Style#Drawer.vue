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
          label="外箱规格编号">
          <a-input placeholder="请输入外箱规格编号" v-decorator="['ctnspecNo', validatorRules.ctnspecNo ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="材料编号">
          <a-input placeholder="请输入材料编号" v-decorator="['matNo', validatorRules.matNo ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="外箱(mm)长">
          <a-input placeholder="请输入外箱(mm)长" v-decorator="['ctnspecLen', validatorRules.ctnspecLen ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="外箱(mm)宽">
          <a-input placeholder="请输入外箱(mm)宽" v-decorator="['ctnspecWid', validatorRules.ctnspecWid ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="外箱(mm)高">
          <a-input placeholder="请输入外箱(mm)高" v-decorator="['ctnspecHig', validatorRules.ctnspecHig ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="材积(CBM)">
          <a-input placeholder="请输入材积(CBM)" v-decorator="['ctnMeasu', validatorRules.ctnMeasu ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="重量(KG)">
          <a-input placeholder="请输入重量(KG)" v-decorator="['ctnWgt', validatorRules.ctnWgt ]" />
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
          label="异动时间">
          <a-input placeholder="请输入异动时间" v-decorator="['modifyDt', {}]" />
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
    name: "CtnspecModal",
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
        ctnspecNo:{rules: [{ required: true, message: '请输入外箱规格编号!' }]},
        matNo:{rules: [{ required: true, message: '请输入材料编号!' }]},
        ctnspecLen:{rules: [{ required: true, message: '请输入外箱(mm)长!' }]},
        ctnspecWid:{rules: [{ required: true, message: '请输入外箱(mm)宽!' }]},
        ctnspecHig:{rules: [{ required: true, message: '请输入外箱(mm)高!' }]},
        ctnMeasu:{rules: [{ required: true, message: '请输入材积(CBM)!' }]},
        ctnWgt:{rules: [{ required: true, message: '请输入重量(KG)!' }]},
        },
        url: {
          add: "/order/ctnspec/add",
          edit: "/order/ctnspec/edit",
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
          this.form.setFieldsValue(pick(this.model,'factNo','ctnspecNo','matNo','ctnspecLen','ctnspecWid','ctnspecHig','ctnMeasu','ctnWgt','userNo','modifyDt'))
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