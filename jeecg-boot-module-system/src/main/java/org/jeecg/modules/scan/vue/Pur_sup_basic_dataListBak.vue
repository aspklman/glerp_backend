<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="公司编号">
              <a-input placeholder="请输入公司编号" v-model="queryParam.factNo"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="SKU">
              <a-input placeholder="请输入SKU" v-model="queryParam.sku"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="SPU">
              <a-input placeholder="请输入SPU" v-model="queryParam.spu"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="EAN">
              <a-input placeholder="请输入EAN" v-model="queryParam.ean"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="质检标准">
              <a-input placeholder="请输入质检标准" v-model="queryParam.qualityInspectStd"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('SKU基础资料')">导出</a-button>
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
    <pur_sup_basic_data-modal ref="modalForm" @ok="modalFormOk"></pur_sup_basic_data-modal>
  </a-card>
</template>

<script>
  import Pur_sup_basic_dataModal from './modules/Pur_sup_basic_dataModalBak'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "Pur_sup_basic_dataList",
    mixins:[JeecgListMixin],
    components: {
      Pur_sup_basic_dataModal
    },
    data () {
      return {
        description: 'SKU基础资料管理页面',
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
            title: '公司编号',
            align:"center",
            dataIndex: 'factNo'
           },
		   {
            title: 'SKU',
            align:"center",
            dataIndex: 'sku'
           },
		   {
            title: 'SPU',
            align:"center",
            dataIndex: 'spu'
           },
		   {
            title: 'EAN',
            align:"center",
            dataIndex: 'ean'
           },
		   {
            title: '质检标准',
            align:"center",
            dataIndex: 'qualityInspectStd'
           },
		   {
            title: '所属分类类目',
            align:"center",
            dataIndex: 'classification'
           },
		   {
            title: '开发序列号',
            align:"center",
            dataIndex: 'developSn'
           },
		   {
            title: '开发链接',
            align:"center",
            dataIndex: 'developLink'
           },
		   {
            title: '状态',
            align:"center",
            dataIndex: 'status'
           },
		   {
            title: 'SKU采购名称',
            align:"center",
            dataIndex: 'skuPurchaseNm'
           },
		   {
            title: '英文名称',
            align:"center",
            dataIndex: 'engNm'
           },
		   {
            title: '速卖通英文名称',
            align:"center",
            dataIndex: 'engNmSmt'
           },
		   {
            title: '速卖通最佳LISTING',
            align:"center",
            dataIndex: 'bestListingSmt'
           },
		   {
            title: 'EBAY最佳LISTING',
            align:"center",
            dataIndex: 'bestListingEbay'
           },
		   {
            title: '长(自带包装)',
            align:"center",
            dataIndex: 'netLength'
           },
		   {
            title: '宽(自带包装)',
            align:"center",
            dataIndex: 'netWidth'
           },
		   {
            title: '高(自带包装)',
            align:"center",
            dataIndex: 'netHeight'
           },
		   {
            title: '长(打包后)',
            align:"center",
            dataIndex: 'grossLength'
           },
		   {
            title: '宽(打包后)',
            align:"center",
            dataIndex: 'grossWidth'
           },
		   {
            title: '高(打包后)',
            align:"center",
            dataIndex: 'grossHeight'
           },
		   {
            title: 'SKU物流属性',
            align:"center",
            dataIndex: 'wlAttribute'
           },
		   {
            title: '海关编码',
            align:"center",
            dataIndex: 'customsCode'
           },
		   {
            title: '税率',
            align:"center",
            dataIndex: 'taxRate'
           },
		   {
            title: '参考价',
            align:"center",
            dataIndex: 'refPrice'
           },
		   {
            title: '重量(自带包装)',
            align:"center",
            dataIndex: 'netWeight'
           },
		   {
            title: '重量(打包后)',
            align:"center",
            dataIndex: 'grossWeight'
           },
		   {
            title: '储位',
            align:"center",
            dataIndex: 'storageArea'
           },
		   {
            title: '采购开发',
            align:"center",
            dataIndex: 'purchaseDevelopEmp'
           },
		   {
            title: '订货员',
            align:"center",
            dataIndex: 'orderGoodsEmp'
           },
		   {
            title: '订货主管',
            align:"center",
            dataIndex: 'orderGoodsDirector'
           },
		   {
            title: '计划员',
            align:"center",
            dataIndex: 'planner'
           },
		   {
            title: '备注',
            align:"center",
            dataIndex: 'remarks'
           },
		   {
            title: '关键词',
            align:"center",
            dataIndex: 'keywords'
           },
		   {
            title: '容量(液体或电池类产品)',
            align:"center",
            dataIndex: 'capacitys'
           },
		   {
            title: '产品属性',
            align:"center",
            dataIndex: 'prodAttribute'
           },
		   {
            title: '产品多属性信息',
            align:"center",
            dataIndex: 'prodAttributeInfo'
           },
		   {
            title: '产品信息',
            align:"center",
            dataIndex: 'prodInfo'
           },
		   {
            title: '合规图',
            align:"center",
            dataIndex: 'compliancePicture'
           },
		   {
            title: '合规审核结果',
            align:"center",
            dataIndex: 'complianceAuditResult'
           },
		   {
            title: '风险等级',
            align:"center",
            dataIndex: 'riskLevel'
           },
		   {
            title: '不可上架平台站点',
            align:"center",
            dataIndex: 'noUploadSite'
           },
		   {
            title: '制式',
            align:"center",
            dataIndex: 'form'
           },
		   {
            title: '型号',
            align:"center",
            dataIndex: 'modelNo'
           },
		   {
            title: '产品说明书',
            align:"center",
            dataIndex: 'prodInstructions'
           },
		   {
            title: '执行标准',
            align:"center",
            dataIndex: 'execStandard'
           },
		   {
            title: '摄影师',
            align:"center",
            dataIndex: 'photographer'
           },
		   {
            title: '图片美工',
            align:"center",
            dataIndex: 'photoProcesser'
           },
		   {
            title: '英文编辑',
            align:"center",
            dataIndex: 'engEditor'
           },
		   {
            title: '效果跟进员',
            align:"center",
            dataIndex: 'effectFollower'
           },
		   {
            title: '异动人',
            align:"center",
            dataIndex: 'userNo'
           },
		   {
            title: '异动日期时间',
            align:"center",
            dataIndex: 'modifyDt'
           },
		   {
            title: '序号',
            align:"center",
            dataIndex: 'sN'
           },
		   {
            title: '批号',
            align:"center",
            dataIndex: 'lotNo'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/scan/pur_sup_basic_data/list",
          delete: "/scan/pur_sup_basic_data/delete",
          deleteBatch: "/scan/pur_sup_basic_data/deleteBatch",
          exportXlsUrl: "scan/pur_sup_basic_data/exportXls",
          importExcelUrl: "scan/pur_sup_basic_data/importExcel",
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