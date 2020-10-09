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
          <a-input placeholder="请输入factNo" v-decorator="['factNo', {}]" />
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
          label="fbilltypeid">
          <a-input placeholder="请输入fbilltypeid" v-decorator="['fbilltypeid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fbillno">
          <a-input placeholder="请输入fbillno" v-decorator="['fbillno', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fbusinesstype">
          <a-input placeholder="请输入fbusinesstype" v-decorator="['fbusinesstype', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fdate">
          <a-date-picker v-decorator="[ 'fdate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fenddate">
          <a-date-picker v-decorator="[ 'fenddate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fcustomerid">
          <a-input placeholder="请输入fcustomerid" v-decorator="['fcustomerid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fcurrencyid">
          <a-input placeholder="请输入fcurrencyid" v-decorator="['fcurrencyid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fsettleorgid">
          <a-input placeholder="请输入fsettleorgid" v-decorator="['fsettleorgid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fsaleorgid">
          <a-input placeholder="请输入fsaleorgid" v-decorator="['fsaleorgid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fownertype">
          <a-input placeholder="请输入fownertype" v-decorator="['fownertype', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fownerid">
          <a-input placeholder="请输入fownerid" v-decorator="['fownerid', {}]" />
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
          label="fwrittenoffstatus">
          <a-input placeholder="请输入fwrittenoffstatus" v-decorator="['fwrittenoffstatus', {}]" />
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
          label="fwrittenoffamountfor">
          <a-input placeholder="请输入fwrittenoffamountfor" v-decorator="['fwrittenoffamountfor', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fnotwrittenoffamountfor">
          <a-input placeholder="请输入fnotwrittenoffamountfor" v-decorator="['fnotwrittenoffamountfor', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="frelatehadpayamount">
          <a-input placeholder="请输入frelatehadpayamount" v-decorator="['frelatehadpayamount', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fwrittenoffamount">
          <a-input placeholder="请输入fwrittenoffamount" v-decorator="['fwrittenoffamount', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fnotwrittenoffamount">
          <a-input placeholder="请输入fnotwrittenoffamount" v-decorator="['fnotwrittenoffamount', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fdocumentstatus">
          <a-input placeholder="请输入fdocumentstatus" v-decorator="['fdocumentstatus', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fcreatorid">
          <a-input placeholder="请输入fcreatorid" v-decorator="['fcreatorid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fcreatedate">
          <a-date-picker v-decorator="[ 'fcreatedate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fmodifierid">
          <a-input placeholder="请输入fmodifierid" v-decorator="['fmodifierid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fmodifydate">
          <a-date-picker v-decorator="[ 'fmodifydate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="faccountsystem">
          <a-input placeholder="请输入faccountsystem" v-decorator="['faccountsystem', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fistax">
          <a-input placeholder="请输入fistax" v-decorator="['fistax', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fbyverify">
          <a-input placeholder="请输入fbyverify" v-decorator="['fbyverify', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fcancelstatus">
          <a-input placeholder="请输入fcancelstatus" v-decorator="['fcancelstatus', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fcancellerid">
          <a-input placeholder="请输入fcancellerid" v-decorator="['fcancellerid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fcanceldate">
          <a-date-picker v-decorator="[ 'fcanceldate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fapproverid">
          <a-input placeholder="请输入fapproverid" v-decorator="['fapproverid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fapprovedate">
          <a-date-picker v-decorator="[ 'fapprovedate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fpayconditon">
          <a-input placeholder="请输入fpayconditon" v-decorator="['fpayconditon', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fsaledeptid">
          <a-input placeholder="请输入fsaledeptid" v-decorator="['fsaledeptid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fsalegroupid">
          <a-input placeholder="请输入fsalegroupid" v-decorator="['fsalegroupid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fsaleerid">
          <a-input placeholder="请输入fsaleerid" v-decorator="['fsaleerid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fcreditcheckresult">
          <a-input placeholder="请输入fcreditcheckresult" v-decorator="['fcreditcheckresult', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fisinit">
          <a-input placeholder="请输入fisinit" v-decorator="['fisinit', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fisretail">
          <a-input placeholder="请输入fisretail" v-decorator="['fisretail', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fcashsale">
          <a-input placeholder="请输入fcashsale" v-decorator="['fcashsale', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fsalesbustype">
          <a-input placeholder="请输入fsalesbustype" v-decorator="['fsalesbustype', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fpayorgid">
          <a-input placeholder="请输入fpayorgid" v-decorator="['fpayorgid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fmatchmethodid">
          <a-input placeholder="请输入fmatchmethodid" v-decorator="['fmatchmethodid', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fispriceexcludetax">
          <a-input placeholder="请输入fispriceexcludetax" v-decorator="['fispriceexcludetax', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fremark">
          <a-input placeholder="请输入fremark" v-decorator="['fremark', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fsetaccounttype">
          <a-input placeholder="请输入fsetaccounttype" v-decorator="['fsetaccounttype', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fishookmatch">
          <a-input placeholder="请输入fishookmatch" v-decorator="['fishookmatch', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fiswriteoff">
          <a-input placeholder="请输入fiswriteoff" v-decorator="['fiswriteoff', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="fisinvoicearlier">
          <a-input placeholder="请输入fisinvoicearlier" v-decorator="['fisinvoicearlier', {}]" />
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
    name: "TArReceivableModal",
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
          add: "/finance/tArReceivable/add",
          edit: "/finance/tArReceivable/edit",
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
          this.form.setFieldsValue(pick(this.model,'factNo','fid','fbilltypeid','fbillno','fbusinesstype','fcustomerid','fcurrencyid','fsettleorgid','fsaleorgid','fownertype','fownerid','fallamountfor','fwrittenoffstatus','fopenstatus','fwrittenoffamountfor','fnotwrittenoffamountfor','frelatehadpayamount','fwrittenoffamount','fnotwrittenoffamount','fdocumentstatus','fcreatorid','fmodifierid','faccountsystem','fistax','fbyverify','fcancelstatus','fcancellerid','fapproverid','fpayconditon','fsaledeptid','fsalegroupid','fsaleerid','fcreditcheckresult','fisinit','fisretail','fcashsale','fsalesbustype','fpayorgid','fmatchmethodid','fispriceexcludetax','fremark','fsetaccounttype','fishookmatch','fiswriteoff','fisinvoicearlier'))
		  //时间格式化
          this.form.setFieldsValue({fdate:this.model.fdate?moment(this.model.fdate):null})
          this.form.setFieldsValue({fenddate:this.model.fenddate?moment(this.model.fenddate):null})
          this.form.setFieldsValue({fcreatedate:this.model.fcreatedate?moment(this.model.fcreatedate):null})
          this.form.setFieldsValue({fmodifydate:this.model.fmodifydate?moment(this.model.fmodifydate):null})
          this.form.setFieldsValue({fcanceldate:this.model.fcanceldate?moment(this.model.fcanceldate):null})
          this.form.setFieldsValue({fapprovedate:this.model.fapprovedate?moment(this.model.fapprovedate):null})
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
            formData.fdate = formData.fdate?formData.fdate.format():null;
            formData.fenddate = formData.fenddate?formData.fenddate.format():null;
            formData.fcreatedate = formData.fcreatedate?formData.fcreatedate.format():null;
            formData.fmodifydate = formData.fmodifydate?formData.fmodifydate.format():null;
            formData.fcanceldate = formData.fcanceldate?formData.fcanceldate.format():null;
            formData.fapprovedate = formData.fapprovedate?formData.fapprovedate.format():null;
            
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