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
          <a-input placeholder="请输入厂区编号" v-decorator="['factNo', validatorRules.factNo ]" />
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
          label="组别名称">
          <a-input placeholder="请输入组别名称" v-decorator="['secNm', validatorRules.secNm ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="组别归属制程字符串">
          <a-input placeholder="请输入组别归属制程字符串" v-decorator="['cardNm', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="组别类型">
          <a-input placeholder="请输入组别类型" v-decorator="['secKind', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="制程编号">
          <a-input placeholder="请输入制程编号" v-decorator="['borNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="SEC_LEVEL">
          <a-input placeholder="请输入SEC_LEVEL" v-decorator="['secLevel', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="归属厂别式组别">
          <a-input placeholder="请输入归属厂别式组别" v-decorator="['belSec', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="SAL_RMK">
          <a-input placeholder="请输入SAL_RMK" v-decorator="['salRmk', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="AIM_RATE">
          <a-input placeholder="请输入AIM_RATE" v-decorator="['aimRate', validatorRules.aimRate ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="AWARD_RMK">
          <a-input placeholder="请输入AWARD_RMK" v-decorator="['awardRmk', validatorRules.awardRmk ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="ID_NO">
          <a-input placeholder="请输入ID_NO" v-decorator="['idNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="BOARD_NO">
          <a-input placeholder="请输入BOARD_NO" v-decorator="['boardNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="组别备注">
          <a-input placeholder="请输入组别备注" v-decorator="['secDesc', {}]" />
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
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="归属派工阶段deptm表">
          <a-input placeholder="请输入归属派工阶段deptm表" v-decorator="['deptNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="生产厂别">
          <a-input placeholder="请输入生产厂别" v-decorator="['proFact', validatorRules.proFact ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="UP_SEC">
          <a-input placeholder="请输入UP_SEC" v-decorator="['upSec', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="锁定注记">
          <a-input placeholder="请输入锁定注记" v-decorator="['lockRmk', validatorRules.lockRmk ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="锁定日期">
          <a-input placeholder="请输入锁定日期" v-decorator="['lockDate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="MANAGER_PER">
          <a-input placeholder="请输入MANAGER_PER" v-decorator="['managerPer', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="REL_TEL">
          <a-input placeholder="请输入REL_TEL" v-decorator="['relTel', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="PROXY_PER">
          <a-input placeholder="请输入PROXY_PER" v-decorator="['proxyPer', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="ASS_PER">
          <a-input placeholder="请输入ASS_PER" v-decorator="['assPer', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="SECOND_PROXY">
          <a-input placeholder="请输入SECOND_PROXY" v-decorator="['secondProxy', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="归属产量阶段stgm表">
          <a-input placeholder="请输入归属产量阶段stgm表" v-decorator="['borDept', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="LINE_NO">
          <a-input placeholder="请输入LINE_NO" v-decorator="['lineNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="GROUP_NO">
          <a-input placeholder="请输入GROUP_NO" v-decorator="['groupNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="TIMING_KIND">
          <a-input placeholder="请输入TIMING_KIND" v-decorator="['timingKind', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="派工生产厂别注记">
          <a-input placeholder="请输入派工生产厂别注记" v-decorator="['proType', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="阶段名称">
          <a-input placeholder="请输入阶段名称" v-decorator="['proflNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="COUNT_ITEM">
          <a-input placeholder="请输入COUNT_ITEM" v-decorator="['countItem', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="AWARD_SEC">
          <a-input placeholder="请输入AWARD_SEC" v-decorator="['awardSec', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="secSimpleNm">
          <a-input placeholder="请输入secSimpleNm" v-decorator="['secSimpleNm', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="secId">
          <a-input placeholder="请输入secId" v-decorator="['secId', {}]" />
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
    name: "SecModal",
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
        secNo:{rules: [{ required: true, message: '请输入组别编号!' }]},
        secNm:{rules: [{ required: true, message: '请输入组别名称!' }]},
        aimRate:{rules: [{ required: true, message: '请输入AIM_RATE!' }]},
        awardRmk:{rules: [{ required: true, message: '请输入AWARD_RMK!' }]},
        proFact:{rules: [{ required: true, message: '请输入生产厂别!' }]},
        lockRmk:{rules: [{ required: true, message: '请输入锁定注记!' }]},
        },
        url: {
          add: "/product/sec/add",
          edit: "/product/sec/edit",
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
          this.form.setFieldsValue(pick(this.model,'factNo','secNo','secNm','cardNm','secKind','borNo','secLevel','belSec','salRmk','aimRate','awardRmk','idNo','boardNo','secDesc','userNo','modifyDt','deptNo','proFact','upSec','lockRmk','lockDate','managerPer','relTel','proxyPer','assPer','secondProxy','borDept','lineNo','groupNo','timingKind','proType','proflNo','countItem','awardSec','secSimpleNm','secId'))
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