<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="厂别">
              <a-input placeholder="请输入厂别" v-model="queryParam.factNo"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="生产厂别编号">
              <a-input placeholder="请输入生产厂别编号" v-model="queryParam.proFact"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="生产厂别名称">
              <a-input placeholder="请输入生产厂别名称" v-model="queryParam.proFactNm"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="生产类别">
              <a-input placeholder="请输入生产类别" v-model="queryParam.proKind"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="FACT_NUM">
              <a-input placeholder="请输入FACT_NUM" v-model="queryParam.factNum"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('生产工厂表')">导出</a-button>
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
    <profactm-modal ref="modalForm" @ok="modalFormOk"></profactm-modal>
  </a-card>
</template>

<script>
  import ProfactmModal from './modules/ProfactmModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "ProfactmList",
    mixins:[JeecgListMixin],
    components: {
      ProfactmModal
    },
    data () {
      return {
        description: '生产工厂表管理页面',
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
            title: '厂别',
            align:"center",
            dataIndex: 'factNo'
           },
		   {
            title: '生产厂别编号',
            align:"center",
            dataIndex: 'proFact'
           },
		   {
            title: '生产厂别名称',
            align:"center",
            dataIndex: 'proFactNm'
           },
		   {
            title: '生产类别',
            align:"center",
            dataIndex: 'proKind'
           },
		   {
            title: 'FACT_NUM',
            align:"center",
            dataIndex: 'factNum'
           },
		   {
            title: 'PROD_KIND_NM',
            align:"center",
            dataIndex: 'prodKindNm'
           },
		   {
            title: '排序编号',
            align:"center",
            dataIndex: 'factSeq'
           },
		   {
            title: '生产类别串',
            align:"center",
            dataIndex: 'proKinds'
           },
		   {
            title: '地址',
            align:"center",
            dataIndex: 'addr'
           },
		   {
            title: '电话',
            align:"center",
            dataIndex: 'tel'
           },
		   {
            title: '传真',
            align:"center",
            dataIndex: 'fax'
           },
		   {
            title: '利润中心',
            align:"center",
            dataIndex: 'belProfitCenter'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/bom/profactm/list",
          delete: "/bom/profactm/delete",
          deleteBatch: "/bom/profactm/deleteBatch",
          exportXlsUrl: "bom/profactm/exportXls",
          importExcelUrl: "bom/profactm/importExcel",
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