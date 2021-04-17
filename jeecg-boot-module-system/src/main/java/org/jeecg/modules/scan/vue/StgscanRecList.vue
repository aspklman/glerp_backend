<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="厂区编号">
              <a-input placeholder="请输入厂区编号" v-model="queryParam.factNo"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="阶段编号">
              <a-input placeholder="请输入阶段编号" v-model="queryParam.stgNo"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="条码编号">
              <a-input placeholder="请输入条码编号" v-model="queryParam.barNo"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="接收日期">
              <a-input placeholder="请输入接收日期" v-model="queryParam.recDate"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="接收时间">
              <a-input placeholder="请输入接收时间" v-model="queryParam.recTime"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('成品入库扫描(卡洛驰)')">导出</a-button>
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
    <stgscanRec-modal ref="modalForm" @ok="modalFormOk"></stgscanRec-modal>
  </a-card>
</template>

<script>
  import StgscanRecModal from './modules/StgscanRecModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "StgscanRecList",
    mixins:[JeecgListMixin],
    components: {
      StgscanRecModal
    },
    data () {
      return {
        description: '成品入库扫描(卡洛驰)管理页面',
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
            title: '厂区编号',
            align:"center",
            dataIndex: 'factNo'
           },
		   {
            title: '阶段编号',
            align:"center",
            dataIndex: 'stgNo'
           },
		   {
            title: '条码编号',
            align:"center",
            dataIndex: 'barNo'
           },
		   {
            title: '接收日期',
            align:"center",
            dataIndex: 'recDate'
           },
		   {
            title: '接收时间',
            align:"center",
            dataIndex: 'recTime'
           },
		   {
            title: '组别编号',
            align:"center",
            dataIndex: 'secNo'
           },
		   {
            title: '入库单号',
            align:"center",
            dataIndex: 'inNo'
           },
		   {
            title: '库别',
            align:"center",
            dataIndex: 'stkNo'
           },
		   {
            title: '存放区编号',
            align:"center",
            dataIndex: 'locateNo'
           },
		   {
            title: '电脑IP',
            align:"center",
            dataIndex: 'scanIp'
           },
		   {
            title: '生产日期',
            align:"center",
            dataIndex: 'proDate'
           },
		   {
            title: '异动人',
            align:"center",
            dataIndex: 'userNo'
           },
		   {
            title: '生产厂别',
            align:"center",
            dataIndex: 'proFact'
           },
		   {
            title: '产生入库单标记(0:未产生 1:已产生 2:待产生)',
            align:"center",
            dataIndex: 'genInStockRmk'
           },
		   {
            title: '存放地点',
            align:"center",
            dataIndex: 'locArea'
           },
		   {
            title: 'locNoOut',
            align:"center",
            dataIndex: 'locNoOut'
           },
		   {
            title: 'locAreaOut',
            align:"center",
            dataIndex: 'locAreaOut'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/scan/stgscanRec/list",
          delete: "/scan/stgscanRec/delete",
          deleteBatch: "/scan/stgscanRec/deleteBatch",
          exportXlsUrl: "scan/stgscanRec/exportXls",
          importExcelUrl: "scan/stgscanRec/importExcel",
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