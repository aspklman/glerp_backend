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
            <a-form-item label="fid">
              <a-input placeholder="请输入fid" v-model="queryParam.fid"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="fbilltypeid">
              <a-input placeholder="请输入fbilltypeid" v-model="queryParam.fbilltypeid"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="fbillno">
              <a-input placeholder="请输入fbillno" v-model="queryParam.fbillno"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="fbusinesstype">
              <a-input placeholder="请输入fbusinesstype" v-model="queryParam.fbusinesstype"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('应收单表头')">导出</a-button>
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
    <tArReceivable-modal ref="modalForm" @ok="modalFormOk"></tArReceivable-modal>
  </a-card>
</template>

<script>
  import TArReceivableModal from './modules/TArReceivableModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "TArReceivableList",
    mixins:[JeecgListMixin],
    components: {
      TArReceivableModal
    },
    data () {
      return {
        description: '应收单表头管理页面',
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
            title: 'fid',
            align:"center",
            dataIndex: 'fid'
           },
		   {
            title: 'fbilltypeid',
            align:"center",
            dataIndex: 'fbilltypeid'
           },
		   {
            title: 'fbillno',
            align:"center",
            dataIndex: 'fbillno'
           },
		   {
            title: 'fbusinesstype',
            align:"center",
            dataIndex: 'fbusinesstype'
           },
		   {
            title: 'fdate',
            align:"center",
            dataIndex: 'fdate'
           },
		   {
            title: 'fenddate',
            align:"center",
            dataIndex: 'fenddate'
           },
		   {
            title: 'fcustomerid',
            align:"center",
            dataIndex: 'fcustomerid'
           },
		   {
            title: 'fcurrencyid',
            align:"center",
            dataIndex: 'fcurrencyid'
           },
		   {
            title: 'fsettleorgid',
            align:"center",
            dataIndex: 'fsettleorgid'
           },
		   {
            title: 'fsaleorgid',
            align:"center",
            dataIndex: 'fsaleorgid'
           },
		   {
            title: 'fownertype',
            align:"center",
            dataIndex: 'fownertype'
           },
		   {
            title: 'fownerid',
            align:"center",
            dataIndex: 'fownerid'
           },
		   {
            title: 'fallamountfor',
            align:"center",
            dataIndex: 'fallamountfor'
           },
		   {
            title: 'fwrittenoffstatus',
            align:"center",
            dataIndex: 'fwrittenoffstatus'
           },
		   {
            title: 'fopenstatus',
            align:"center",
            dataIndex: 'fopenstatus'
           },
		   {
            title: 'fwrittenoffamountfor',
            align:"center",
            dataIndex: 'fwrittenoffamountfor'
           },
		   {
            title: 'fnotwrittenoffamountfor',
            align:"center",
            dataIndex: 'fnotwrittenoffamountfor'
           },
		   {
            title: 'frelatehadpayamount',
            align:"center",
            dataIndex: 'frelatehadpayamount'
           },
		   {
            title: 'fwrittenoffamount',
            align:"center",
            dataIndex: 'fwrittenoffamount'
           },
		   {
            title: 'fnotwrittenoffamount',
            align:"center",
            dataIndex: 'fnotwrittenoffamount'
           },
		   {
            title: 'fdocumentstatus',
            align:"center",
            dataIndex: 'fdocumentstatus'
           },
		   {
            title: 'fcreatorid',
            align:"center",
            dataIndex: 'fcreatorid'
           },
		   {
            title: 'fcreatedate',
            align:"center",
            dataIndex: 'fcreatedate'
           },
		   {
            title: 'fmodifierid',
            align:"center",
            dataIndex: 'fmodifierid'
           },
		   {
            title: 'fmodifydate',
            align:"center",
            dataIndex: 'fmodifydate'
           },
		   {
            title: 'faccountsystem',
            align:"center",
            dataIndex: 'faccountsystem'
           },
		   {
            title: 'fistax',
            align:"center",
            dataIndex: 'fistax'
           },
		   {
            title: 'fbyverify',
            align:"center",
            dataIndex: 'fbyverify'
           },
		   {
            title: 'fcancelstatus',
            align:"center",
            dataIndex: 'fcancelstatus'
           },
		   {
            title: 'fcancellerid',
            align:"center",
            dataIndex: 'fcancellerid'
           },
		   {
            title: 'fcanceldate',
            align:"center",
            dataIndex: 'fcanceldate'
           },
		   {
            title: 'fapproverid',
            align:"center",
            dataIndex: 'fapproverid'
           },
		   {
            title: 'fapprovedate',
            align:"center",
            dataIndex: 'fapprovedate'
           },
		   {
            title: 'fpayconditon',
            align:"center",
            dataIndex: 'fpayconditon'
           },
		   {
            title: 'fsaledeptid',
            align:"center",
            dataIndex: 'fsaledeptid'
           },
		   {
            title: 'fsalegroupid',
            align:"center",
            dataIndex: 'fsalegroupid'
           },
		   {
            title: 'fsaleerid',
            align:"center",
            dataIndex: 'fsaleerid'
           },
		   {
            title: 'fcreditcheckresult',
            align:"center",
            dataIndex: 'fcreditcheckresult'
           },
		   {
            title: 'fisinit',
            align:"center",
            dataIndex: 'fisinit'
           },
		   {
            title: 'fisretail',
            align:"center",
            dataIndex: 'fisretail'
           },
		   {
            title: 'fcashsale',
            align:"center",
            dataIndex: 'fcashsale'
           },
		   {
            title: 'fsalesbustype',
            align:"center",
            dataIndex: 'fsalesbustype'
           },
		   {
            title: 'fpayorgid',
            align:"center",
            dataIndex: 'fpayorgid'
           },
		   {
            title: 'fmatchmethodid',
            align:"center",
            dataIndex: 'fmatchmethodid'
           },
		   {
            title: 'fispriceexcludetax',
            align:"center",
            dataIndex: 'fispriceexcludetax'
           },
		   {
            title: 'fremark',
            align:"center",
            dataIndex: 'fremark'
           },
		   {
            title: 'fsetaccounttype',
            align:"center",
            dataIndex: 'fsetaccounttype'
           },
		   {
            title: 'fishookmatch',
            align:"center",
            dataIndex: 'fishookmatch'
           },
		   {
            title: 'fiswriteoff',
            align:"center",
            dataIndex: 'fiswriteoff'
           },
		   {
            title: 'fisinvoicearlier',
            align:"center",
            dataIndex: 'fisinvoicearlier'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/finance/tArReceivable/list",
          delete: "/finance/tArReceivable/delete",
          deleteBatch: "/finance/tArReceivable/deleteBatch",
          exportXlsUrl: "finance/tArReceivable/exportXls",
          importExcelUrl: "finance/tArReceivable/importExcel",
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