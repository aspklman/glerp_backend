<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="feedback1">
              <a-input placeholder="请输入feedback1" v-model="queryParam.feedback1"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="feedback2">
              <a-input placeholder="请输入feedback2" v-model="queryParam.feedback2"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="factNo">
              <a-input placeholder="请输入factNo" v-model="queryParam.factNo"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="proFact">
              <a-input placeholder="请输入proFact" v-model="queryParam.proFact"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="secNo">
              <a-input placeholder="请输入secNo" v-model="queryParam.secNo"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('成型品质扫描')">导出</a-button>
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
    <stgscanQuality-modal ref="modalForm" @ok="modalFormOk"></stgscanQuality-modal>
  </a-card>
</template>

<script>
  import StgscanQualityModal from './modules/StgscanQualityModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "StgscanQualityList",
    mixins:[JeecgListMixin],
    components: {
      StgscanQualityModal
    },
    data () {
      return {
        description: '成型品质扫描管理页面',
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
            title: 'feedback1',
            align:"center",
            dataIndex: 'feedback1'
           },
		   {
            title: 'feedback2',
            align:"center",
            dataIndex: 'feedback2'
           },
		   {
            title: 'factNo',
            align:"center",
            dataIndex: 'factNo'
           },
		   {
            title: 'proFact',
            align:"center",
            dataIndex: 'proFact'
           },
		   {
            title: 'secNo',
            align:"center",
            dataIndex: 'secNo'
           },
		   {
            title: 'styleNo',
            align:"center",
            dataIndex: 'styleNo'
           },
		   {
            title: 'styleGender',
            align:"center",
            dataIndex: 'styleGender'
           },
		   {
            title: 'styleColor',
            align:"center",
            dataIndex: 'styleColor'
           },
		   {
            title: 'badLevel',
            align:"center",
            dataIndex: 'badLevel'
           },
		   {
            title: 'badNo',
            align:"center",
            dataIndex: 'badNo'
           },
		   {
            title: 'qty',
            align:"center",
            dataIndex: 'qty'
           },
		   {
            title: 'recDate',
            align:"center",
            dataIndex: 'recDate'
           },
		   {
            title: 'recTime',
            align:"center",
            dataIndex: 'recTime'
           },
		   {
            title: 'userNo',
            align:"center",
            dataIndex: 'userNo'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/scan/stgscanQuality/list",
          delete: "/scan/stgscanQuality/delete",
          deleteBatch: "/scan/stgscanQuality/deleteBatch",
          exportXlsUrl: "scan/stgscanQuality/exportXls",
          importExcelUrl: "scan/stgscanQuality/importExcel",
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