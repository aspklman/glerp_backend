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
              <a-input placeholder="请输入厂区编号" v-decorator="['factNo', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="客户订单编号">
              <a-input placeholder="请输入客户订单编号" v-decorator="['custOdrNo', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="版本编号">
              <a-input placeholder="请输入版本编号" v-decorator="['versionNo', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="型体编号">
              <a-input placeholder="请输入型体编号" v-decorator="['styleShorten', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="Pace编码">
              <a-input placeholder="请输入Pace编码" v-decorator="['paceCode', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="模具名称和颜色">
              <a-input placeholder="请输入模具名称和颜色" v-decorator="['modelColour', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="订单类型">
              <a-input placeholder="请输入订单类型" v-decorator="['orderType', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="验货日期">
              <a-date-picker
                placeholder="请输入验货日期"
                style="width:100%"
                v-decorator="[ 'inspectDate', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="验货结果">
              <a-input placeholder="请输入验货结果" v-decorator="['inspectorDecision', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
      </a-form>

      <!-- 子表单区域 -->
      <a-tabs v-model="activeKey" @change="handleChangeTabs">
        <a-tab-pane tab="验货报告子表" :key="refKeys[0]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[0]"
            :loading="sampleInspectReportDTable.loading"
            :columns="sampleInspectReportDTable.columns"
            :dataSource="sampleInspectReportDTable.dataSource"
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
    name: 'SampleInspectReportMModal',
    mixins: [JEditableTableMixin],
    data() {
      return {
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
        },
        refKeys: ['sampleInspectReportD', ],
        activeKey: 'sampleInspectReportD',
        // 验货报告子表
        sampleInspectReportDTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '厂区编号',
              key: 'factNo',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '客户订单编号',
              key: 'custOdrNo',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '版本编号',
              key: 'versionNo',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '型体编号',
              key: 'styleShorten',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '主分类编号',
              key: 'itemMainNo',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '中分类编号',
              key: 'itemMediumNo',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '检查点编号',
              key: 'checkPointsNo',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '次要',
              key: 'minor',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '主要',
              key: 'major',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '重要',
              key: 'critical',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '次要可用状态',
              key: 'minorEnable',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '主要可用状态',
              key: 'majorEnable',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '重要可用状态',
              key: 'criticalEnable',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '验货报告主表ID',
              key: 'mainId',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
          ]
        },
        url: {
          add: "/quality/sampleInspectReportM/add",
          edit: "/quality/sampleInspectReportM/edit",
          sampleInspectReportD: {
            list: '/quality/sampleInspectReportM/querySampleInspectReportDByMainId'
          },
        }
      }
    },
    methods: {
 
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model, 'factNo', 'custOdrNo', 'versionNo', 'styleShorten', 'paceCode', 'modelColour', 'orderType', 'inspectDate', 'inspectorDecision', ))
          // 时间格式化
          this.form.setFieldsValue({ inspectDate: this.model.inspectDate ? moment(this.model.inspectDate) : null })
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.sampleInspectReportD.list, params, this.sampleInspectReportDTable)
        }
      },
 
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        //时间格式化
        main.inspectDate = main.inspectDate ? main.inspectDate.format() : null;
        return {
          ...main, // 展开
          sampleInspectReportDList: allValues.tablesValue[0].values,
        }
      }
    }
  }
</script>

<style scoped>
</style>