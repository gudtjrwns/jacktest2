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
            <button type="button" class="btn btn-sm btn-danger">
              <i class="far fa-edit"></i> 삭제
            </button>
          </div>
        </div>
      </div>
      <div class="pull-right">
        <div class="form-group">
          <div class="input-group">
            <input type="text" class="form-control col-6" placeholder="검색어를 입력해주세요.">
            <span class="input-group-btn">
              <button type="button" class="btn btn-sm btn-primary">검색</button>
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
          <td><input type="checkbox" /></td>
          <td>No</td>
          <td>제목</td>
          <td>작성자</td>
          <td>등록일</td>
          <td>수정</td>
          <td>삭제</td>
        </tr>
        </thead>
        <tbody>
        <tr v-for="notice in notices" :key="notice.id">
          <td><input type="checkbox" /></td>
          <td>{{notice.id}}</td>
          <td>{{notice.title}}</td>
          <td>{{notice.writer}}</td>
          <td>{{notice.credate}}</td>
          <td><button @click="editNotice(notice.id)" class="btn btn-sm btn-info">수정</button></td>
          <td><button @click="deleteNotice(notice.id)" class="btn btn-sm btn-danger">삭제</button></td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Home',
    data() {
      return {
        notices:[

        ]
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
        console.log(index);
      },
      editNotice(index) {
        this.$router.push({
          name: 'edit',
          params: {
            id: index
          }
        })
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
