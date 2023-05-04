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
          label="厂区编号">
          <a-input placeholder="请输入厂区编号" v-decorator="['factNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="异动单号">
          <a-input placeholder="请输入异动单号" v-decorator="['changeNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="异动日期">
          <a-input placeholder="请输入异动日期" v-decorator="['changeDt', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="原状态">
          <a-input placeholder="请输入原状态" v-decorator="['stateOld', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="原厂别">
          <a-input placeholder="请输入原厂别" v-decorator="['factOld', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="原绩效中心">
          <a-input placeholder="请输入原绩效中心" v-decorator="['factCentreOld', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="原部门">
          <a-input placeholder="请输入原部门" v-decorator="['deptnoOld', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="原存放位置">
          <a-input placeholder="请输入原存放位置" v-decorator="['locationOld', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="原保管人">
          <a-input placeholder="请输入原保管人" v-decorator="['custodianOld', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="原管理经理">
          <a-input placeholder="请输入原管理经理" v-decorator="['custodianManagerOld', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="原使用人">
          <a-input placeholder="请输入原使用人" v-decorator="['custodianUserOld', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="原存放楼层">
          <a-input placeholder="请输入原存放楼层" v-decorator="['locationFlOld', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="原存放区域">
          <a-input placeholder="请输入原存放区域" v-decorator="['locationDlOld', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="原存放厂区">
          <a-input placeholder="请输入原存放厂区" v-decorator="['locationFactOld', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="现状态">
          <a-input placeholder="请输入现状态" v-decorator="['state', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="现厂别">
          <a-input placeholder="请输入现厂别" v-decorator="['fact', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="现绩效中心">
          <a-input placeholder="请输入现绩效中心" v-decorator="['factCentre', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="现部门">
          <a-input placeholder="请输入现部门" v-decorator="['deptno', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="现存放位置">
          <a-input placeholder="请输入现存放位置" v-decorator="['location', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="现保管人">
          <a-input placeholder="请输入现保管人" v-decorator="['custodian', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="现管理经理">
          <a-input placeholder="请输入现管理经理" v-decorator="['custodianManager', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="现使用人">
          <a-input placeholder="请输入现使用人" v-decorator="['custodianUser', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="现存放楼层">
          <a-input placeholder="请输入现存放楼层" v-decorator="['locationFl', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="现存放区域">
          <a-input placeholder="请输入现存放区域" v-decorator="['locationDl', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="现存放厂区">
          <a-input placeholder="请输入现存放厂区" v-decorator="['locationFact', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="备注">
          <a-input placeholder="请输入备注" v-decorator="['notes', {}]" />
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
    name: "AssetChangeModal",
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
        },
        url: {
          add: "/asset/assetChange/add",
          edit: "/asset/assetChange/edit",
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
          this.form.setFieldsValue(pick(this.model,'factNo','changeNo','changeDt','stateOld','factOld','factCentreOld','deptnoOld','locationOld','custodianOld','custodianManagerOld','custodianUserOld','locationFlOld','locationDlOld','locationFactOld','state','fact','factCentre','deptno','location','custodian','custodianManager','custodianUser','locationFl','locationDl','locationFact','notes'))
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