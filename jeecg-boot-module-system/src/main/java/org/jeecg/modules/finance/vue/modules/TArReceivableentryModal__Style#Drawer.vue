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
          label="factNo">
          <a-input placeholder="请输入factNo" v-decorator="['factNo', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fentryid">
          <a-input placeholder="请输入fentryid" v-decorator="['fentryid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fid">
          <a-input placeholder="请输入fid" v-decorator="['fid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fseq">
          <a-input placeholder="请输入fseq" v-decorator="['fseq', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fsourcetype">
          <a-input placeholder="请输入fsourcetype" v-decorator="['fsourcetype', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fsourcebillno">
          <a-input placeholder="请输入fsourcebillno" v-decorator="['fsourcebillno', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fsrcrowid">
          <a-input placeholder="请输入fsrcrowid" v-decorator="['fsrcrowid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fmaterialid">
          <a-input placeholder="请输入fmaterialid" v-decorator="['fmaterialid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fprice">
          <a-input placeholder="请输入fprice" v-decorator="['fprice', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="ftaxprice">
          <a-input placeholder="请输入ftaxprice" v-decorator="['ftaxprice', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fpricecoefficient">
          <a-input placeholder="请输入fpricecoefficient" v-decorator="['fpricecoefficient', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fpriceunitid">
          <a-input placeholder="请输入fpriceunitid" v-decorator="['fpriceunitid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fpriceqty">
          <a-input placeholder="请输入fpriceqty" v-decorator="['fpriceqty', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fentrydiscountrate">
          <a-input placeholder="请输入fentrydiscountrate" v-decorator="['fentrydiscountrate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fdiscountamountfor">
          <a-input placeholder="请输入fdiscountamountfor" v-decorator="['fdiscountamountfor', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fentrytaxrate">
          <a-input placeholder="请输入fentrytaxrate" v-decorator="['fentrytaxrate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fpricewithtax">
          <a-input placeholder="请输入fpricewithtax" v-decorator="['fpricewithtax', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fnotaxamountfor">
          <a-input placeholder="请输入fnotaxamountfor" v-decorator="['fnotaxamountfor', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="ftaxamountfor">
          <a-input placeholder="请输入ftaxamountfor" v-decorator="['ftaxamountfor', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fallamountfor">
          <a-input placeholder="请输入fallamountfor" v-decorator="['fallamountfor', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fdiscountamount">
          <a-input placeholder="请输入fdiscountamount" v-decorator="['fdiscountamount', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fnotaxamount">
          <a-input placeholder="请输入fnotaxamount" v-decorator="['fnotaxamount', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="ftaxamount">
          <a-input placeholder="请输入ftaxamount" v-decorator="['ftaxamount', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fallamount">
          <a-input placeholder="请输入fallamount" v-decorator="['fallamount', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fbasicunitid">
          <a-input placeholder="请输入fbasicunitid" v-decorator="['fbasicunitid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fbasicunitqty">
          <a-input placeholder="请输入fbasicunitqty" v-decorator="['fbasicunitqty', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fwrittenoffstatus">
          <a-input placeholder="请输入fwrittenoffstatus" v-decorator="['fwrittenoffstatus', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fwrittenoffamountfor">
          <a-input placeholder="请输入fwrittenoffamountfor" v-decorator="['fwrittenoffamountfor', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fwrittenoffqty">
          <a-input placeholder="请输入fwrittenoffqty" v-decorator="['fwrittenoffqty', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fopenqty">
          <a-input placeholder="请输入fopenqty" v-decorator="['fopenqty', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fopenstatus">
          <a-input placeholder="请输入fopenstatus" v-decorator="['fopenstatus', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fopenamountfor">
          <a-input placeholder="请输入fopenamountfor" v-decorator="['fopenamountfor', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fbuyivqty">
          <a-input placeholder="请输入fbuyivqty" v-decorator="['fbuyivqty', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fbuyivbasicqty">
          <a-input placeholder="请输入fbuyivbasicqty" v-decorator="['fbuyivbasicqty', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fivallamountfor">
          <a-input placeholder="请输入fivallamountfor" v-decorator="['fivallamountfor', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fordernumber">
          <a-input placeholder="请输入fordernumber" v-decorator="['fordernumber', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fcomment">
          <a-input placeholder="请输入fcomment" v-decorator="['fcomment', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fbasejoinqty">
          <a-input placeholder="请输入fbasejoinqty" v-decorator="['fbasejoinqty', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="ftaxcombination">
          <a-input placeholder="请输入ftaxcombination" v-decorator="['ftaxcombination', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fsrccreditamountfor">
          <a-input placeholder="请输入fsrccreditamountfor" v-decorator="['fsrccreditamountfor', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fbillcreditamountfor">
          <a-input placeholder="请输入fbillcreditamountfor" v-decorator="['fbillcreditamountfor', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fexeccreditamountfor">
          <a-input placeholder="请输入fexeccreditamountfor" v-decorator="['fexeccreditamountfor', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fclosecreditamountfor">
          <a-input placeholder="请输入fclosecreditamountfor" v-decorator="['fclosecreditamountfor', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fmaterialdesc">
          <a-input placeholder="请输入fmaterialdesc" v-decorator="['fmaterialdesc', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="ftemptaxrate">
          <a-input placeholder="请输入ftemptaxrate" v-decorator="['ftemptaxrate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fmatchnottaxamtfor">
          <a-input placeholder="请输入fmatchnottaxamtfor" v-decorator="['fmatchnottaxamtfor', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fhadmatchamountfor">
          <a-input placeholder="请输入fhadmatchamountfor" v-decorator="['fhadmatchamountfor', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fspecialqty">
          <a-input placeholder="请输入fspecialqty" v-decorator="['fspecialqty', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fspecialamountfor">
          <a-input placeholder="请输入fspecialamountfor" v-decorator="['fspecialamountfor', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fbflowid">
          <a-input placeholder="请输入fbflowid" v-decorator="['fbflowid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fmonumber">
          <a-input placeholder="请输入fmonumber" v-decorator="['fmonumber', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fmoentryseq">
          <a-input placeholder="请输入fmoentryseq" v-decorator="['fmoentryseq', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fopno">
          <a-input placeholder="请输入fopno" v-decorator="['fopno', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fseqnumber">
          <a-input placeholder="请输入fseqnumber" v-decorator="['fseqnumber', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fopernumber">
          <a-input placeholder="请输入fopernumber" v-decorator="['fopernumber', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fprocessid">
          <a-input placeholder="请输入fprocessid" v-decorator="['fprocessid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="ffprodepartmentid">
          <a-input placeholder="请输入ffprodepartmentid" v-decorator="['ffprodepartmentid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fcustmatid">
          <a-input placeholder="请输入fcustmatid" v-decorator="['fcustmatid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fcustmatname">
          <a-input placeholder="请输入fcustmatname" v-decorator="['fcustmatname', {}]" />
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
    name: "TArReceivableentryModal",
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
          add: "/finance/tArReceivableentry/add",
          edit: "/finance/tArReceivableentry/edit",
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
          this.form.setFieldsValue(pick(this.model,'factNo','fentryid','fid','fseq','fsourcetype','fsourcebillno','fsrcrowid','fmaterialid','fprice','ftaxprice','fpricecoefficient','fpriceunitid','fpriceqty','fentrydiscountrate','fdiscountamountfor','fentrytaxrate','fpricewithtax','fnotaxamountfor','ftaxamountfor','fallamountfor','fdiscountamount','fnotaxamount','ftaxamount','fallamount','fbasicunitid','fbasicunitqty','fwrittenoffstatus','fwrittenoffamountfor','fwrittenoffqty','fopenqty','fopenstatus','fopenamountfor','fbuyivqty','fbuyivbasicqty','fivallamountfor','fordernumber','fcomment','fbasejoinqty','ftaxcombination','fsrccreditamountfor','fbillcreditamountfor','fexeccreditamountfor','fclosecreditamountfor','fmaterialdesc','ftemptaxrate','fmatchnottaxamtfor','fhadmatchamountfor','fspecialqty','fspecialamountfor','fbflowid','fmonumber','fmoentryseq','fopno','fseqnumber','fopernumber','fprocessid','ffprodepartmentid','fcustmatid','fcustmatname'))
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