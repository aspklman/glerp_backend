<template>
  <a-modal
    :title="title"
    :width="1200"
    :visible="visible"
    :maskClosable="false"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel">
    <a-spin :spinning="confirmLoading">
      <!-- 主表单区域 -->
      <a-form :form="form">
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="厂区编号">
              <a-input placeholder="请输入厂区编号" v-decorator="['factNo', validatorRules.factNo ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="型体编号">
              <a-input placeholder="请输入型体编号" v-decorator="['styleNo', validatorRules.styleNo ]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="客户型体编号">
              <a-input placeholder="请输入客户型体编号" v-decorator="['articNo', validatorRules.articNo ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="型体名称">
              <a-input placeholder="请输入型体名称" v-decorator="['styleNm', validatorRules.styleNm ]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="财务型体简称,财务成本中心维护">
              <a-input placeholder="请输入财务型体简称,财务成本中心维护" v-decorator="['styleShorten', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="底模编号">
              <a-input placeholder="请输入底模编号" v-decorator="['moldNo', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="超始BOM确认日">
              <a-input placeholder="请输入超始BOM确认日" v-decorator="['bomStcfmDate', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="起始用量确认日">
              <a-input placeholder="请输入起始用量确认日" v-decorator="['stdqStcfmDate', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="性别">
              <a-input placeholder="请输入性别" v-decorator="['gender', validatorRules.gender ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="楦头编号">
              <a-input placeholder="请输入楦头编号" v-decorator="['lastNo', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="英文颜色">
              <a-input placeholder="请输入英文颜色" v-decorator="['styleEngColor', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="型体颜色">
              <a-input placeholder="请输入型体颜色" v-decorator="['styleColor', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="型体工艺类别">
              <a-input placeholder="请输入型体工艺类别" v-decorator="['solColor', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="商品编号">
              <a-input placeholder="请输入商品编号" v-decorator="['goodsNo', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="序号">
              <a-input placeholder="请输入序号" v-decorator="['goodsSeq', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="品牌编号">
              <a-input placeholder="请输入品牌编号" v-decorator="['brandNo', validatorRules.brandNo ]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="鞋型类别编号">
              <a-input placeholder="请输入鞋型类别编号" v-decorator="['styletypeNo', validatorRules.styletypeNo ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="异动人">
              <a-input placeholder="请输入异动人" v-decorator="['userNo', validatorRules.userNo ]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="异动时间">
              <a-input placeholder="请输入异动时间" v-decorator="['modifyDt', validatorRules.modifyDt ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="型体单价">
              <a-input placeholder="请输入型体单价" v-decorator="['price', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="通用型体名称">
              <a-input placeholder="请输入通用型体名称" v-decorator="['univModelName', validatorRules.univModelName ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="报价SIZE编号">
              <a-input placeholder="请输入报价SIZE编号" v-decorator="['quotationSizeNo', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="报价楦头肥度">
              <a-input placeholder="请输入报价楦头肥度" v-decorator="['quotationLastWidth', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="纸版编号">
              <a-input placeholder="请输入纸版编号" v-decorator="['paperNo', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="客户型体代码">
              <a-input placeholder="请输入客户型体代码" v-decorator="['vampPartNo', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="客户颜色代码">
              <a-input placeholder="请输入客户颜色代码" v-decorator="['cusColorNo', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="来源型体">
              <a-input placeholder="请输入来源型体" v-decorator="['sourNo', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="转印纸注记">
              <a-input placeholder="请输入转印纸注记" v-decorator="['htpRmk', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="转印纸用量">
              <a-input placeholder="请输入转印纸用量" v-decorator="['htpQty', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="取消注记">
              <a-input placeholder="请输入取消注记" v-decorator="['cnlRmk', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="取消日期">
              <a-input placeholder="请输入取消日期" v-decorator="['cnlDate', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="CUSTRAN_RMK">
              <a-input placeholder="请输入CUSTRAN_RMK" v-decorator="['custranRmk', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="CUSTRAN_DT">
              <a-input placeholder="请输入CUSTRAN_DT" v-decorator="['custranDt', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="ARTIC_NM">
              <a-input placeholder="请输入ARTIC_NM" v-decorator="['articNm', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="BOM审核人">
              <a-input placeholder="请输入BOM审核人" v-decorator="['bomVerifyUser', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="BOM审核时间">
              <a-input placeholder="请输入BOM审核时间" v-decorator="['bomVerifyDt', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="型体单位">
              <a-input placeholder="请输入型体单位" v-decorator="['styleUnit', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="soletypeNo">
              <a-input placeholder="请输入soletypeNo" v-decorator="['soletypeNo', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>

      <!-- 子表单区域 -->
      <a-tabs v-model="activeKey" @change="handleChangeTabs">
        <a-tab-pane tab="鞋型SIZE表" :key="refKeys[0]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[0]"
            :loading="stylesTable.loading"
            :columns="stylesTable.columns"
            :dataSource="stylesTable.dataSource"
            :maxHeight="300"
            :rowNumber="true"
            :rowSelection="true"
            :actionButton="true"/>
        </a-tab-pane>
      </a-tabs>

    </a-spin>
  </a-modal>
</template>

<script>

  import moment from 'moment'
  import pick from 'lodash.pick'
  import { FormTypes } from '@/utils/JEditableTableUtil'
  import { JEditableTableMixin } from '@/mixins/JEditableTableMixin'

  export default {
    name: 'StylemModal',
    mixins: [JEditableTableMixin],
    data() {
      return {
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
          factNo: { rules: [{ required: true, message: '请输入厂区编号!' }] },
          styleNo: { rules: [{ required: true, message: '请输入型体编号!' }] },
          articNo: { rules: [{ required: true, message: '请输入客户型体编号!' }] },
          styleNm: { rules: [{ required: true, message: '请输入型体名称!' }] },
          gender: { rules: [{ required: true, message: '请输入性别!' }] },
          brandNo: { rules: [{ required: true, message: '请输入品牌编号!' }] },
          styletypeNo: { rules: [{ required: true, message: '请输入鞋型类别编号!' }] },
          userNo: { rules: [{ required: true, message: '请输入异动人!' }] },
          modifyDt: { rules: [{ required: true, message: '请输入异动时间!' }] },
          univModelName: { rules: [{ required: true, message: '请输入通用型体名称!' }] },
        },
        refKeys: ['styles', ],
        activeKey: 'styles',
        // 鞋型SIZE表
        stylesTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '厂区编号',
              key: 'factNo',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '型体编号',
              key: 'styleNo',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: 'SIZE编号',
              key: 'sizeNo',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: 'SKU_NO',
              key: 'skuNo',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '鞋型基本资料表ID',
              key: 'styleId',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
          ]
        },
        url: {
          add: "/bom/stylem/add",
          edit: "/bom/stylem/edit",
          styles: {
            list: '/bom/stylem/queryStylesByMainId'
          },
        }
      }
    },
    methods: {
 
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model, 'factNo', 'styleNo', 'articNo', 'styleNm', 'styleShorten', 'moldNo', 'bomStcfmDate', 'stdqStcfmDate', 'gender', 'lastNo', 'styleEngColor', 'styleColor', 'solColor', 'goodsNo', 'goodsSeq', 'brandNo', 'styletypeNo', 'userNo', 'modifyDt', 'price', 'univModelName', 'quotationSizeNo', 'quotationLastWidth', 'paperNo', 'vampPartNo', 'cusColorNo', 'sourNo', 'htpRmk', 'htpQty', 'cnlRmk', 'cnlDate', 'custranRmk', 'custranDt', 'articNm', 'bomVerifyUser', 'bomVerifyDt', 'styleUnit', 'soletypeNo', ))
          // 时间格式化
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.styles.list, params, this.stylesTable)
        }
      },
 
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        //时间格式化
        return {
          ...main, // 展开
          stylesList: allValues.tablesValue[0].values,
        }
      }
    }
  }
</script>

<style scoped>
</style>