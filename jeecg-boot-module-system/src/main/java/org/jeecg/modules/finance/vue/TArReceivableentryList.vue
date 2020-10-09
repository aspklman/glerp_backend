<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="factNo">
              <a-input placeholder="请输入factNo" v-model="queryParam.factNo"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="fentryid">
              <a-input placeholder="请输入fentryid" v-model="queryParam.fentryid"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="fid">
              <a-input placeholder="请输入fid" v-model="queryParam.fid"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="fseq">
              <a-input placeholder="请输入fseq" v-model="queryParam.fseq"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="fsourcetype">
              <a-input placeholder="请输入fsourcetype" v-model="queryParam.fsourcetype"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('应收明细')">导出</a-button>
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
    <tArReceivableentry-modal ref="modalForm" @ok="modalFormOk"></tArReceivableentry-modal>
  </a-card>
</template>

<script>
  import TArReceivableentryModal from './modules/TArReceivableentryModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "TArReceivableentryList",
    mixins:[JeecgListMixin],
    components: {
      TArReceivableentryModal
    },
    data () {
      return {
        description: '应收明细管理页面',
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
            title: 'factNo',
            align:"center",
            dataIndex: 'factNo'
           },
		   {
            title: 'fentryid',
            align:"center",
            dataIndex: 'fentryid'
           },
		   {
            title: 'fid',
            align:"center",
            dataIndex: 'fid'
           },
		   {
            title: 'fseq',
            align:"center",
            dataIndex: 'fseq'
           },
		   {
            title: 'fsourcetype',
            align:"center",
            dataIndex: 'fsourcetype'
           },
		   {
            title: 'fsourcebillno',
            align:"center",
            dataIndex: 'fsourcebillno'
           },
		   {
            title: 'fsrcrowid',
            align:"center",
            dataIndex: 'fsrcrowid'
           },
		   {
            title: 'fmaterialid',
            align:"center",
            dataIndex: 'fmaterialid'
           },
		   {
            title: 'fprice',
            align:"center",
            dataIndex: 'fprice'
           },
		   {
            title: 'ftaxprice',
            align:"center",
            dataIndex: 'ftaxprice'
           },
		   {
            title: 'fpricecoefficient',
            align:"center",
            dataIndex: 'fpricecoefficient'
           },
		   {
            title: 'fpriceunitid',
            align:"center",
            dataIndex: 'fpriceunitid'
           },
		   {
            title: 'fpriceqty',
            align:"center",
            dataIndex: 'fpriceqty'
           },
		   {
            title: 'fentrydiscountrate',
            align:"center",
            dataIndex: 'fentrydiscountrate'
           },
		   {
            title: 'fdiscountamountfor',
            align:"center",
            dataIndex: 'fdiscountamountfor'
           },
		   {
            title: 'fentrytaxrate',
            align:"center",
            dataIndex: 'fentrytaxrate'
           },
		   {
            title: 'fpricewithtax',
            align:"center",
            dataIndex: 'fpricewithtax'
           },
		   {
            title: 'fnotaxamountfor',
            align:"center",
            dataIndex: 'fnotaxamountfor'
           },
		   {
            title: 'ftaxamountfor',
            align:"center",
            dataIndex: 'ftaxamountfor'
           },
		   {
            title: 'fallamountfor',
            align:"center",
            dataIndex: 'fallamountfor'
           },
		   {
            title: 'fdiscountamount',
            align:"center",
            dataIndex: 'fdiscountamount'
           },
		   {
            title: 'fnotaxamount',
            align:"center",
            dataIndex: 'fnotaxamount'
           },
		   {
            title: 'ftaxamount',
            align:"center",
            dataIndex: 'ftaxamount'
           },
		   {
            title: 'fallamount',
            align:"center",
            dataIndex: 'fallamount'
           },
		   {
            title: 'fbasicunitid',
            align:"center",
            dataIndex: 'fbasicunitid'
           },
		   {
            title: 'fbasicunitqty',
            align:"center",
            dataIndex: 'fbasicunitqty'
           },
		   {
            title: 'fwrittenoffstatus',
            align:"center",
            dataIndex: 'fwrittenoffstatus'
           },
		   {
            title: 'fwrittenoffamountfor',
            align:"center",
            dataIndex: 'fwrittenoffamountfor'
           },
		   {
            title: 'fwrittenoffqty',
            align:"center",
            dataIndex: 'fwrittenoffqty'
           },
		   {
            title: 'fopenqty',
            align:"center",
            dataIndex: 'fopenqty'
           },
		   {
            title: 'fopenstatus',
            align:"center",
            dataIndex: 'fopenstatus'
           },
		   {
            title: 'fopenamountfor',
            align:"center",
            dataIndex: 'fopenamountfor'
           },
		   {
            title: 'fbuyivqty',
            align:"center",
            dataIndex: 'fbuyivqty'
           },
		   {
            title: 'fbuyivbasicqty',
            align:"center",
            dataIndex: 'fbuyivbasicqty'
           },
		   {
            title: 'fivallamountfor',
            align:"center",
            dataIndex: 'fivallamountfor'
           },
		   {
            title: 'fordernumber',
            align:"center",
            dataIndex: 'fordernumber'
           },
		   {
            title: 'fcomment',
            align:"center",
            dataIndex: 'fcomment'
           },
		   {
            title: 'fbasejoinqty',
            align:"center",
            dataIndex: 'fbasejoinqty'
           },
		   {
            title: 'ftaxcombination',
            align:"center",
            dataIndex: 'ftaxcombination'
           },
		   {
            title: 'fsrccreditamountfor',
            align:"center",
            dataIndex: 'fsrccreditamountfor'
           },
		   {
            title: 'fbillcreditamountfor',
            align:"center",
            dataIndex: 'fbillcreditamountfor'
           },
		   {
            title: 'fexeccreditamountfor',
            align:"center",
            dataIndex: 'fexeccreditamountfor'
           },
		   {
            title: 'fclosecreditamountfor',
            align:"center",
            dataIndex: 'fclosecreditamountfor'
           },
		   {
            title: 'fmaterialdesc',
            align:"center",
            dataIndex: 'fmaterialdesc'
           },
		   {
            title: 'ftemptaxrate',
            align:"center",
            dataIndex: 'ftemptaxrate'
           },
		   {
            title: 'fmatchnottaxamtfor',
            align:"center",
            dataIndex: 'fmatchnottaxamtfor'
           },
		   {
            title: 'fhadmatchamountfor',
            align:"center",
            dataIndex: 'fhadmatchamountfor'
           },
		   {
            title: 'fspecialqty',
            align:"center",
            dataIndex: 'fspecialqty'
           },
		   {
            title: 'fspecialamountfor',
            align:"center",
            dataIndex: 'fspecialamountfor'
           },
		   {
            title: 'fbflowid',
            align:"center",
            dataIndex: 'fbflowid'
           },
		   {
            title: 'fmonumber',
            align:"center",
            dataIndex: 'fmonumber'
           },
		   {
            title: 'fmoentryseq',
            align:"center",
            dataIndex: 'fmoentryseq'
           },
		   {
            title: 'fopno',
            align:"center",
            dataIndex: 'fopno'
           },
		   {
            title: 'fseqnumber',
            align:"center",
            dataIndex: 'fseqnumber'
           },
		   {
            title: 'fopernumber',
            align:"center",
            dataIndex: 'fopernumber'
           },
		   {
            title: 'fprocessid',
            align:"center",
            dataIndex: 'fprocessid'
           },
		   {
            title: 'ffprodepartmentid',
            align:"center",
            dataIndex: 'ffprodepartmentid'
           },
		   {
            title: 'fcustmatid',
            align:"center",
            dataIndex: 'fcustmatid'
           },
		   {
            title: 'fcustmatname',
            align:"center",
            dataIndex: 'fcustmatname'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/finance/tArReceivableentry/list",
          delete: "/finance/tArReceivableentry/delete",
          deleteBatch: "/finance/tArReceivableentry/deleteBatch",
          exportXlsUrl: "finance/tArReceivableentry/exportXls",
          importExcelUrl: "finance/tArReceivableentry/importExcel",
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