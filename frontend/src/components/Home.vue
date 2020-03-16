<template>
  <div id="Home">
    <div>
      <div class="pull-left">
        <div class="form-inline">
          <div class="form-group">
            <button @click="$router.push('add')" class="btn btn-sm btn-info">신규 등록</button>
<!--            <button type="button" class="btn btn-sm btn-info">-->
<!--              <i class="far fa-edit"></i> 신규등록-->
<!--            </button>-->
          </div>
          <div class="form-group">
            <button type="button" @click="deleteAll()" class="btn btn-sm btn-danger">
              <i class="far fa-edit"></i> 삭제
            </button>
          </div>
        </div>
      </div>
      <div class="pull-right">
        <div class="form-group">
          <div class="input-group">
            <input v-model="keyword" type="text" class="form-control col-6" placeholder="검색어를 입력해주세요.">
            <span class="input-group-btn">
              <button type="button" @click="search()"  class="btn btn-sm btn-primary">검색</button>
            </span>
          </div>
        </div>
      </div>
    </div>

    <div>
      <table class="table table-striped table-bordered table-hover">
        <colgroup>
          <col width="8%">
          <col width="8%">
          <col width="*%">
          <col width="15%">
          <col width="15%">
          <col width="8%">
          <col width="8%">
        </colgroup>
        <thead class="thead-dark text-center">
        <tr>
          <td><input type="checkbox" v-model="checkAll" /></td>
          <td>No</td>
          <td>제목</td>
          <td>작성자</td>
          <td>등록일</td>
          <td>수정</td>
          <td>삭제</td>
        </tr>
        </thead>
        <tbody>
        <tr class="alert alert-info" role="alert" v-if="notices.length === 0">
          <td colspan="7"><b>Info!</b> 등록된 게시글이 없습니다. 신규 등록해주세요.</td>
        </tr>
        <tr v-for="notice in notices" :key="notice.id">
          <td><input type="checkbox" :value="notice.id" v-model="checked" /></td>
          <td>{{notice.id}}</td>
          <td><a v-b-modal.modal-1 style="cursor: pointer;" @click="modalShow(notice.id)">{{notice.title}}</a></td>
          <td>{{notice.writer}}</td>
          <td>{{notice.credate}}</td>
          <td><button @click="editNotice(notice.id)" class="btn btn-sm btn-info">수정</button></td>
          <td><button @click="deleteNotice(notice.id)" class="btn btn-sm btn-danger">삭제</button></td>
        </tr>
        </tbody>
      </table>
    </div>

    <div>
      <b-modal id="modal-1" title="게시글 정보" ok-only ok-title="닫기" size="lg">
        <table class="table table-bordered table-striped text-center m-b-0">
          <tbody>
            {{modalNoticeList}}
          </tbody>
        </table>
      </b-modal>
    </div>

  </div>
</template>

<script>

  export default {
    name: 'Home',
    data() {
      return {
        notices:[],
        checked: [],
        keyword: '',
        modalNoticeList: ''
      }
    },
    created() {
      axios.get('http://localhost:8080/notices')
        .then(response => {
          this.notices = response.data.data.content;
        })
        .catch(e => {
          console.log('error : ', e)
        });
    },
    methods: {
      deleteNotice(index) {

        axios.delete('http://localhost:8080/notices/'+index)
          .then(response => {
            alert("삭제 성공!");
            location.reload();
          })
          .catch(e => {
            console.log('error : ', e)
          });

      },
      deleteAll() {
        var idList = this.checked;

        if (idList.length === 0) {alert("선택된 항목이 없습니다."); return false;}

        axios.delete('http://localhost:8080/notices/' + idList)
          .then(response => {
            alert("삭제 성공!");
            location.reload();
          })
          .catch(e => {
            console.log('error : ', e)
          });
      },
      editNotice(index) {
        this.$router.push({
          name: 'edit',
          params: {
            id: index
          }
        })
      },
      search() {
        var keyword = this.keyword;

        axios.get('http://localhost:8080/notices/search', keyword)
          .then(response => {
            this.notices = response.data.data.content;
          })
          .catch(e => {
            console.log('error : ', e)
          });
      },
      modalShow(index) {
        axios.get('http://localhost:8080/notices/'+index)
          .then(response => {
            var data = response.data.data;

            console.log(response.data.data);
            this.modalNoticeList = '<tr><th class="text-center">제목</th><td>' + data.title + '</td></tr>' +
              '<tr><th class="text-center">내용</th><td>' + data.contents + '</td></tr>' +
              '<tr><th class="text-center">작성자</th><td>' + data.writer + '</td></tr>' +
              '<tr><th class="text-center">등록일</th><td>' + data.credate + '</td></tr>' +
              '<tr><th class="text-center">조회수</th><td>' + data.viewcnt + '</td></tr>' +
              '<tr><th class="text-center">댓글수</th><td>' + data.replycnt + '</td></tr>' +
              '<tr><th class="text-center">파일</th><td>' + data.filename + '</td></tr>';
          })
          .catch(e => {
            console.log('error : ', e)
          });
      }
    },
    computed: {
      checkAll: {
        get: function () {
          return this.notices ? this.checked.length === this.notices.length : false;
        },
        set: function (value) {
          var checked = [];

          if (value) {
            this.notices.forEach(function (notice) {
              checked.push(notice.id);
            });
          }

          this.checked = checked;
        }
      }
    }

  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
