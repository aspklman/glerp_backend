<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="转厂注记">
              <a-input placeholder="请输入转厂注记" v-model="queryParam.traFact"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="异动人">
              <a-input placeholder="请输入异动人" v-model="queryParam.userNo"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="异动时间">
              <a-input placeholder="请输入异动时间" v-model="queryParam.modifyDt"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="集团内注记N集团外 Y集团内 E自产车间">
              <a-input placeholder="请输入集团内注记N集团外 Y集团内 E自产车间" v-model="queryParam.mocRmk"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="交易方式">
              <a-input placeholder="请输入交易方式" v-model="queryParam.salBy"></a-input>
            </a-form-item>
          </a-col>
           </template>
          <a-col :md="6" :sm="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('供应商表')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>
    <!-- table区域-end -->

    <!-- 表单区域 -->
    <vendorm-modal ref="modalForm" @ok="modalFormOk"></vendorm-modal>
  </a-card>
</template>

<script>
  import VendormModal from './modules/VendormModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "VendormList",
    mixins:[JeecgListMixin],
    components: {
      VendormModal
    },
    data () {
      return {
        description: '供应商表管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
           },
		   {
            title: '转厂注记',
            align:"center",
            dataIndex: 'traFact'
           },
		   {
            title: '异动人',
            align:"center",
            dataIndex: 'userNo'
           },
		   {
            title: '异动时间',
            align:"center",
            dataIndex: 'modifyDt'
           },
		   {
            title: '集团内注记N集团外 Y集团内 E自产车间',
            align:"center",
            dataIndex: 'mocRmk'
           },
		   {
            title: '交易方式',
            align:"center",
            dataIndex: 'salBy'
           },
		   {
            title: '付款类别',
            align:"center",
            dataIndex: 'payKind'
           },
		   {
            title: '送货厂商',
            align:"center",
            dataIndex: 'sendVendFnm'
           },
		   {
            title: '送货厂别',
            align:"center",
            dataIndex: 'sendFact'
           },
		   {
            title: '海关交易注记(1.直接进口 2.转厂 3.国内购买)',
            align:"center",
            dataIndex: 'cusTradeRmk'
           },
		   {
            title: '指定供应商',
            align:"center",
            dataIndex: 'nbVendor'
           },
		   {
            title: '发货地点',
            align:"center",
            dataIndex: 'shipFromLocation'
           },
		   {
            title: '贸易商简称',
            align:"center",
            dataIndex: 'agentCompanyName'
           },
		   {
            title: '统购注记',
            align:"center",
            dataIndex: 'purRmk'
           },
		   {
            title: '税率',
            align:"center",
            dataIndex: 'taxRate'
           },
		   {
            title: '折扣比率',
            align:"center",
            dataIndex: 'discountRate'
           },
		   {
            title: '填表日期',
            align:"center",
            dataIndex: 'filledDate'
           },
		   {
            title: '英文名称',
            align:"center",
            dataIndex: 'vendEnm'
           },
		   {
            title: '工厂地址',
            align:"center",
            dataIndex: 'vendFactadress'
           },
		   {
            title: '填表日期',
            align:"center",
            dataIndex: 'vendFilleddate'
           },
		   {
            title: '公司成立日期 ',
            align:"center",
            dataIndex: 'vendReviseddate'
           },
		   {
            title: '是否是一般纳税人',
            align:"center",
            dataIndex: 'vendIfcommontax'
           },
		   {
            title: '资本经营模式',
            align:"center",
            dataIndex: 'vendCapitalform'
           },
		   {
            title: '资本额',
            align:"center",
            dataIndex: 'vendCapitalization'
           },
		   {
            title: '平均月营业额',
            align:"center",
            dataIndex: 'vendAveragemonturnover'
           },
		   {
            title: '厂房建筑面积',
            align:"center",
            dataIndex: 'vendFactarea'
           },
		   {
            title: '厂房类别：自有，租赁',
            align:"center",
            dataIndex: 'vendFactkind'
           },
		   {
            title: '往来银行',
            align:"center",
            dataIndex: 'vendBank'
           },
		   {
            title: '往来银行分行',
            align:"center",
            dataIndex: 'vendBankbranch'
           },
		   {
            title: '往来银行帐号',
            align:"center",
            dataIndex: 'vendBankaccount'
           },
		   {
            title: '主要产品',
            align:"center",
            dataIndex: 'vendMainproduction'
           },
		   {
            title: '主要客户',
            align:"center",
            dataIndex: 'vendMaincust'
           },
		   {
            title: '员工概况',
            align:"center",
            dataIndex: 'vendGsituationp'
           },
		   {
            title: '技术合作厂商名称',
            align:"center",
            dataIndex: 'vendNmofteccoopsupplier'
           },
		   {
            title: '关系企业名称',
            align:"center",
            dataIndex: 'vendNmofrelenterprise'
           },
		   {
            title: '主要仪器设备',
            align:"center",
            dataIndex: 'vendMainequipment'
           },
		   {
            title: '是否审核',
            align:"center",
            dataIndex: 'vendIfapproved'
           },
		   {
            title: '审核人',
            align:"center",
            dataIndex: 'vendApprovedby'
           },
		   {
            title: '审核日期',
            align:"center",
            dataIndex: 'vendApproveddate'
           },
		   {
            title: '厂区编号',
            align:"center",
            dataIndex: 'factNo'
           },
		   {
            title: '供应商编号',
            align:"center",
            dataIndex: 'vendNo'
           },
		   {
            title: '国内外别',
            align:"center",
            dataIndex: 'vendKind'
           },
		   {
            title: '供应商简称',
            align:"center",
            dataIndex: 'vendFnm'
           },
		   {
            title: '供应商全名',
            align:"center",
            dataIndex: 'vendGnm'
           },
		   {
            title: '统一编号',
            align:"center",
            dataIndex: 'vendInvno'
           },
		   {
            title: '付款方式',
            align:"center",
            dataIndex: 'paymentKind'
           },
		   {
            title: '交易条件',
            align:"center",
            dataIndex: 'tradeCondition'
           },
		   {
            title: '供应商地址',
            align:"center",
            dataIndex: 'vendAddr'
           },
		   {
            title: '联络电话',
            align:"center",
            dataIndex: 'vendTel'
           },
		   {
            title: '传真号码',
            align:"center",
            dataIndex: 'vendFax'
           },
		   {
            title: '供应商网址',
            align:"center",
            dataIndex: 'vendUrl'
           },
		   {
            title: '联络人',
            align:"center",
            dataIndex: 'contactEer'
           },
		   {
            title: '电子信箱',
            align:"center",
            dataIndex: 'email'
           },
		   {
            title: '负责人',
            align:"center",
            dataIndex: 'vendBoss'
           },
		   {
            title: '海关供应商编号',
            align:"center",
            dataIndex: 'tarVend'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/purchase/vendorm/list",
          delete: "/purchase/vendorm/delete",
          deleteBatch: "/purchase/vendorm/deleteBatch",
          exportXlsUrl: "purchase/vendorm/exportXls",
          importExcelUrl: "purchase/vendorm/importExcel",
       },
    }
  },
  computed: {
    importExcelUrl: function(){
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
    }
  },
    methods: {
     
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>