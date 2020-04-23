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
          label="转厂注记">
          <a-input placeholder="请输入转厂注记" v-decorator="['traFact', validatorRules.traFact ]" />
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
          label="集团内注记N集团外 Y集团内 E自产车间">
          <a-input placeholder="请输入集团内注记N集团外 Y集团内 E自产车间" v-decorator="['mocRmk', validatorRules.mocRmk ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="交易方式">
          <a-input placeholder="请输入交易方式" v-decorator="['salBy', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="付款类别">
          <a-input placeholder="请输入付款类别" v-decorator="['payKind', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="送货厂商">
          <a-input placeholder="请输入送货厂商" v-decorator="['sendVendFnm', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="送货厂别">
          <a-input placeholder="请输入送货厂别" v-decorator="['sendFact', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="海关交易注记(1.直接进口 2.转厂 3.国内购买)">
          <a-input placeholder="请输入海关交易注记(1.直接进口 2.转厂 3.国内购买)" v-decorator="['cusTradeRmk', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="指定供应商">
          <a-input placeholder="请输入指定供应商" v-decorator="['nbVendor', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="发货地点">
          <a-input placeholder="请输入发货地点" v-decorator="['shipFromLocation', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="贸易商简称">
          <a-input placeholder="请输入贸易商简称" v-decorator="['agentCompanyName', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="统购注记">
          <a-input placeholder="请输入统购注记" v-decorator="['purRmk', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="税率">
          <a-input placeholder="请输入税率" v-decorator="['taxRate', validatorRules.taxRate ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="折扣比率">
          <a-input placeholder="请输入折扣比率" v-decorator="['discountRate', validatorRules.discountRate ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="填表日期">
          <a-input placeholder="请输入填表日期" v-decorator="['filledDate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="英文名称">
          <a-input placeholder="请输入英文名称" v-decorator="['vendEnm', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="工厂地址">
          <a-input placeholder="请输入工厂地址" v-decorator="['vendFactadress', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="填表日期">
          <a-input placeholder="请输入填表日期" v-decorator="['vendFilleddate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="公司成立日期 ">
          <a-input placeholder="请输入公司成立日期 " v-decorator="['vendReviseddate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="是否是一般纳税人">
          <a-input placeholder="请输入是否是一般纳税人" v-decorator="['vendIfcommontax', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="资本经营模式">
          <a-input placeholder="请输入资本经营模式" v-decorator="['vendCapitalform', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="资本额">
          <a-input placeholder="请输入资本额" v-decorator="['vendCapitalization', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="平均月营业额">
          <a-input placeholder="请输入平均月营业额" v-decorator="['vendAveragemonturnover', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="厂房建筑面积">
          <a-input placeholder="请输入厂房建筑面积" v-decorator="['vendFactarea', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="厂房类别：自有，租赁">
          <a-input placeholder="请输入厂房类别：自有，租赁" v-decorator="['vendFactkind', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="往来银行">
          <a-input placeholder="请输入往来银行" v-decorator="['vendBank', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="往来银行分行">
          <a-input placeholder="请输入往来银行分行" v-decorator="['vendBankbranch', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="往来银行帐号">
          <a-input placeholder="请输入往来银行帐号" v-decorator="['vendBankaccount', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="主要产品">
          <a-input placeholder="请输入主要产品" v-decorator="['vendMainproduction', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="主要客户">
          <a-input placeholder="请输入主要客户" v-decorator="['vendMaincust', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="员工概况">
          <a-input placeholder="请输入员工概况" v-decorator="['vendGsituationp', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="技术合作厂商名称">
          <a-input placeholder="请输入技术合作厂商名称" v-decorator="['vendNmofteccoopsupplier', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="关系企业名称">
          <a-input placeholder="请输入关系企业名称" v-decorator="['vendNmofrelenterprise', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="主要仪器设备">
          <a-input placeholder="请输入主要仪器设备" v-decorator="['vendMainequipment', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="是否审核">
          <a-input placeholder="请输入是否审核" v-decorator="['vendIfapproved', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="审核人">
          <a-input placeholder="请输入审核人" v-decorator="['vendApprovedby', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="审核日期">
          <a-input placeholder="请输入审核日期" v-decorator="['vendApproveddate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="厂区编号">
          <a-input placeholder="请输入厂区编号" v-decorator="['factNo', validatorRules.factNo ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="供应商编号">
          <a-input placeholder="请输入供应商编号" v-decorator="['vendNo', validatorRules.vendNo ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="国内外别">
          <a-input placeholder="请输入国内外别" v-decorator="['vendKind', validatorRules.vendKind ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="供应商简称">
          <a-input placeholder="请输入供应商简称" v-decorator="['vendFnm', validatorRules.vendFnm ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="供应商全名">
          <a-input placeholder="请输入供应商全名" v-decorator="['vendGnm', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="统一编号">
          <a-input placeholder="请输入统一编号" v-decorator="['vendInvno', validatorRules.vendInvno ]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="付款方式">
          <a-input placeholder="请输入付款方式" v-decorator="['paymentKind', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="交易条件">
          <a-input placeholder="请输入交易条件" v-decorator="['tradeCondition', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="供应商地址">
          <a-input placeholder="请输入供应商地址" v-decorator="['vendAddr', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="联络电话">
          <a-input placeholder="请输入联络电话" v-decorator="['vendTel', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="传真号码">
          <a-input placeholder="请输入传真号码" v-decorator="['vendFax', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="供应商网址">
          <a-input placeholder="请输入供应商网址" v-decorator="['vendUrl', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="联络人">
          <a-input placeholder="请输入联络人" v-decorator="['contactEer', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="电子信箱">
          <a-input placeholder="请输入电子信箱" v-decorator="['email', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="负责人">
          <a-input placeholder="请输入负责人" v-decorator="['vendBoss', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="海关供应商编号">
          <a-input placeholder="请输入海关供应商编号" v-decorator="['tarVend', {}]" />
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
    name: "VendormModal",
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
        traFact:{rules: [{ required: true, message: '请输入转厂注记!' }]},
        mocRmk:{rules: [{ required: true, message: '请输入集团内注记N集团外 Y集团内 E自产车间!' }]},
        taxRate:{rules: [{ required: true, message: '请输入税率!' }]},
        discountRate:{rules: [{ required: true, message: '请输入折扣比率!' }]},
        factNo:{rules: [{ required: true, message: '请输入厂区编号!' }]},
        vendNo:{rules: [{ required: true, message: '请输入供应商编号!' }]},
        vendKind:{rules: [{ required: true, message: '请输入国内外别!' }]},
        vendFnm:{rules: [{ required: true, message: '请输入供应商简称!' }]},
        vendInvno:{rules: [{ required: true, message: '请输入统一编号!' }]},
        },
        url: {
          add: "/purchase/vendorm/add",
          edit: "/purchase/vendorm/edit",
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
          this.form.setFieldsValue(pick(this.model,'traFact','userNo','modifyDt','mocRmk','salBy','payKind','sendVendFnm','sendFact','cusTradeRmk','nbVendor','shipFromLocation','agentCompanyName','purRmk','taxRate','discountRate','filledDate','vendEnm','vendFactadress','vendFilleddate','vendReviseddate','vendIfcommontax','vendCapitalform','vendCapitalization','vendAveragemonturnover','vendFactarea','vendFactkind','vendBank','vendBankbranch','vendBankaccount','vendMainproduction','vendMaincust','vendGsituationp','vendNmofteccoopsupplier','vendNmofrelenterprise','vendMainequipment','vendIfapproved','vendApprovedby','vendApproveddate','factNo','vendNo','vendKind','vendFnm','vendGnm','vendInvno','paymentKind','tradeCondition','vendAddr','vendTel','vendFax','vendUrl','contactEer','email','vendBoss','tarVend'))
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