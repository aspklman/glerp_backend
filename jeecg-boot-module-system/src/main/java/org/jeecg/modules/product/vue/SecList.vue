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
            <a-form-item label="组别编号">
              <a-input placeholder="请输入组别编号" v-model="queryParam.secNo"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="组别名称">
              <a-input placeholder="请输入组别名称" v-model="queryParam.secNm"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="组别归属制程字符串">
              <a-input placeholder="请输入组别归属制程字符串" v-model="queryParam.cardNm"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="组别类型">
              <a-input placeholder="请输入组别类型" v-model="queryParam.secKind"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('生产组别表')">导出</a-button>
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
    <sec-modal ref="modalForm" @ok="modalFormOk"></sec-modal>
  </a-card>
</template>

<script>
  import SecModal from './modules/SecModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "SecList",
    mixins:[JeecgListMixin],
    components: {
      SecModal
    },
    data () {
      return {
        description: '生产组别表管理页面',
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
            title: '组别编号',
            align:"center",
            dataIndex: 'secNo'
           },
		   {
            title: '组别名称',
            align:"center",
            dataIndex: 'secNm'
           },
		   {
            title: '组别归属制程字符串',
            align:"center",
            dataIndex: 'cardNm'
           },
		   {
            title: '组别类型',
            align:"center",
            dataIndex: 'secKind'
           },
		   {
            title: '制程编号',
            align:"center",
            dataIndex: 'borNo'
           },
		   {
            title: 'SEC_LEVEL',
            align:"center",
            dataIndex: 'secLevel'
           },
		   {
            title: '归属厂别式组别',
            align:"center",
            dataIndex: 'belSec'
           },
		   {
            title: 'SAL_RMK',
            align:"center",
            dataIndex: 'salRmk'
           },
		   {
            title: 'AIM_RATE',
            align:"center",
            dataIndex: 'aimRate'
           },
		   {
            title: 'AWARD_RMK',
            align:"center",
            dataIndex: 'awardRmk'
           },
		   {
            title: 'ID_NO',
            align:"center",
            dataIndex: 'idNo'
           },
		   {
            title: 'BOARD_NO',
            align:"center",
            dataIndex: 'boardNo'
           },
		   {
            title: '组别备注',
            align:"center",
            dataIndex: 'secDesc'
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
            title: '归属派工阶段deptm表',
            align:"center",
            dataIndex: 'deptNo'
           },
		   {
            title: '生产厂别',
            align:"center",
            dataIndex: 'proFact'
           },
		   {
            title: 'UP_SEC',
            align:"center",
            dataIndex: 'upSec'
           },
		   {
            title: '锁定注记',
            align:"center",
            dataIndex: 'lockRmk'
           },
		   {
            title: '锁定日期',
            align:"center",
            dataIndex: 'lockDate'
           },
		   {
            title: 'MANAGER_PER',
            align:"center",
            dataIndex: 'managerPer'
           },
		   {
            title: 'REL_TEL',
            align:"center",
            dataIndex: 'relTel'
           },
		   {
            title: 'PROXY_PER',
            align:"center",
            dataIndex: 'proxyPer'
           },
		   {
            title: 'ASS_PER',
            align:"center",
            dataIndex: 'assPer'
           },
		   {
            title: 'SECOND_PROXY',
            align:"center",
            dataIndex: 'secondProxy'
           },
		   {
            title: '归属产量阶段stgm表',
            align:"center",
            dataIndex: 'borDept'
           },
		   {
            title: 'LINE_NO',
            align:"center",
            dataIndex: 'lineNo'
           },
		   {
            title: 'GROUP_NO',
            align:"center",
            dataIndex: 'groupNo'
           },
		   {
            title: 'TIMING_KIND',
            align:"center",
            dataIndex: 'timingKind'
           },
		   {
            title: '派工生产厂别注记',
            align:"center",
            dataIndex: 'proType'
           },
		   {
            title: '阶段名称',
            align:"center",
            dataIndex: 'proflNo'
           },
		   {
            title: 'COUNT_ITEM',
            align:"center",
            dataIndex: 'countItem'
           },
		   {
            title: 'AWARD_SEC',
            align:"center",
            dataIndex: 'awardSec'
           },
		   {
            title: 'secSimpleNm',
            align:"center",
            dataIndex: 'secSimpleNm'
           },
		   {
            title: 'secId',
            align:"center",
            dataIndex: 'secId'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/product/sec/list",
          delete: "/product/sec/delete",
          deleteBatch: "/product/sec/deleteBatch",
          exportXlsUrl: "product/sec/exportXls",
          importExcelUrl: "product/sec/importExcel",
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