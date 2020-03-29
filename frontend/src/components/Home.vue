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

    <div class="row" style="padding-top: 20px;">
      <div class="col-md-offset-5" style="margin: 0 auto;">
        <div class="btn-toolbar">
          <div class="btn-group">
            <button class="btn btn-outline-dark btn-sm" type="button" @click="paginationNotice(startPage-2)"><<</button>
            <button class="btn btn-outline-dark btn-sm" type="button" @click="paginationNotice(currentPage-2)"><</button>

            <button v-for="index in totalPage" :key="index" v-if="currentPage === index"
                    class="btn btn-outline-dark btn-sm active" type="button" @click="paginationNotice(index-1)">{{index}}</button>
            <button v-else
                    class="btn btn-outline-dark btn-sm" type="button" @click="paginationNotice(index-1)">{{index}}</button>

            <button v-if="currentPage === totalPage" class="btn btn-outline-dark btn-sm" type="button">></button>
            <button v-else class="btn btn-outline-dark btn-sm" type="button" @click="paginationNotice(currentPage)">></button>

            <button class="btn btn-outline-dark btn-sm" type="button" @click="paginationNotice(totalPage-1)">>></button>
          </div>
        </div>
      </div>
    </div>

    <div>
      <b-modal id="modal-1" title="게시글 정보" ok-only ok-title="닫기" size="lg">
        <table class="table table-bordered table-striped text-center m-b-0">
          <tbody v-model="noticeOne">
            <tr>
              <th class="text-center">제목</th>
              <td>{{noticeOne.title}}</td>
            </tr>
            <tr>
              <th class="text-center">내용</th>
              <td>{{noticeOne.contents}}</td>
            </tr>
            <tr>
              <th class="text-center">작성자</th>
              <td>{{noticeOne.writer}}</td>
            </tr>
            <tr>
              <th class="text-center">등록일</th>
              <td>{{noticeOne.credate | formatDate}}</td>
            </tr>
            <tr>
              <th class="text-center">조회수</th>
              <td>{{noticeOne.viewcnt}}</td>
            </tr>
            <tr>
              <th class="text-center">댓글수</th>
              <td>{{noticeOne.replycnt}}</td>
            </tr>
            <tr>
              <th class="text-center">다운로드</th>
              <td v-if="noticeOne.filename === 'NONE'">첨부된 파일이 없습니다.</td>
              <td v-else><a @click="downloadFileData(noticeOne.id)" style="color:blue; cursor:pointer;">{{noticeOne.filename}}</a></td>
            </tr>
          </tbody>
        </table>

        <div>
          <h4>댓글</h4>
        </div>
        <div v-if="replyDist === true">
          <textarea v-model="replyContents" name="content" id="contentsAdd" rows="3" class="form-control"
                    type="text" style="resize:none; overflow: auto;"
                    placeholder="댓글을 입력해 주세요."></textarea>
          <input v-model="replyWriter" type="text" id="writerAdd" class="form-control" style="width: 200px;" placeholder="이름을 입력해 주세요.">
          <div class="pull-right" style="margin-top:15px;float:right;">
            <button @click="addReply(noticeOne.id)" type="button" id="replyAddBtn" class="btn btn-sm btn-info">댓글 달기</button>
          </div>
        </div>
        <div v-else>
          <textarea v-model="editContents" name="content" rows="3" class="form-control"
                    type="text" style="resize:none; overflow: auto;"
                    placeholder="댓글을 입력해 주세요."></textarea>
          <div class="pull-right" style="margin-top:15px;float:right;">
            <button @click="editReplyExecute(editReplyId)" type="button" class="btn btn-sm btn-info">댓글 편집</button>
          </div>
        </div>

        <div class="clearfix"></div>
        <div class="ln_solid" style="border-top:1.5px solid gray;"></div>

        <table class="table table-bordered table-striped m-b-0">
          <tbody>
            <tr class="alert alert-info" role="alert" v-if="replies.length === 0">
              <td><b>Info!</b> 등록된 댓글이 없습니다. 새로운 댓글을 작성해주세요.</td>
            </tr>
            <tr v-for="reply in replies" :key="reply.id">
              <td>
                <div>
                  {{reply.contents}}
                  <br/>
                  {{reply.credate}}
                </div>
                <p>{{reply.writer}}</p>
                <div>
                  <span style="color:blue; font-weight:500; cursor:pointer;" @click="editReply(reply.id)">수정</span> / <span style="color:red; font-weight:500; cursor:pointer;" @click="delReply(reply.id, noticeOne.id)">삭제</span>
                </div>
              </td>
            </tr>
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
        notices: [],
        checked: [],
        keyword: '',
        noticeOne: '',
        replies: [],
        replyDist: true,
        replyContents: '',
        replyWriter: '',
        editContents: '',
        editReplyId: 0,
        startPage: 0,
        totalPage: 0,
        currentPage: 0
      }
    },
    created() {
      axios.get('http://localhost:8080/notices')
        .then(response => {
          console.log(response.data.data);

          this.notices = response.data.data.content;
          this.startPage = (Math.ceil((response.data.data.pageable.pageNumber + 1) / 10.0) * 10) - 9;
          this.currentPage = response.data.data.pageable.pageNumber + 1;
          this.totalPage = response.data.data.totalPages;

          console.log("this.startPage - 시작페이지 - ", this.startPage);
          console.log("this.currentPage - 현재페이지 - ", this.currentPage);
          console.log("this.totalPage - 총페이지 - ", this.totalPage);
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

        if(keyword === '' || keyword === null){return false;}

        axios.get('http://localhost:8080/notices/search', {
          params:{
            keyword: keyword
          }
        })
          .then(response => {
            this.notices = response.data.data.content;
          })
          .catch(e => {
            console.log('error : ', e)
          });
      },
      modalShow(index) {
        // 게시글 목록
        axios.get('http://localhost:8080/notices/'+index)
          .then(response => {
            var data = response.data.data;

            this.noticeOne = response.data.data;
          })
          .catch(e => {
            console.log('error : ', e)
          });

        // 댓글 목록
        this.replyDist = true;

        axios.get('http://localhost:8080/replies/notice/'+index)
          .then(response => {
            var data = response.data.data;

            this.replies = response.data.data;
          })
          .catch(e => {
            console.log('error : ', e)
          });
      },
      downloadFileData(index) {
        location.href = 'http://localhost:8080/notices/'+index+'/file';
      },
      addReply(index) {
        const formData = new FormData();

        formData.append("writer", this.replyWriter);
        formData.append("contents", this.replyContents);

        axios.post('http://localhost:8080/replies/notice/' + index, formData)
          .then(response => {

            axios.get('http://localhost:8080/replies/notice/' + response.data.data.noticeid)
              .then(response => {
                this.replies = response.data.data;
                this.replyContents = '';
                this.replyWriter = '';
              })
              .catch(e => {
                console.log('error : ', e)
              });
          })
          .catch(e => {
            console.log('error : ', e)
          });

      },
      editReplyExecute(index) {
        const formData = new FormData();

        formData.append("contents", this.editContents);

        axios.put('http://localhost:8080/replies/' + index, formData)
          .then(response => {
            this.replyDist = true;
            this.editReplyId = 0;

            axios.get('http://localhost:8080/replies/notice/' + response.data.data.noticeid)
              .then(response => {
                this.replies = response.data.data;
              })
              .catch(e => {
                console.log('error : ', e)
              });
          })
          .catch(e => {
            console.log('error : ', e)
          });
      },
      editReply(index) {
        this.replyDist = false;
        this.editReplyId = index;

        axios.get('http://localhost:8080/replies/' + index)
          .then(response => {
            this.editContents = response.data.data.contents;
          })
          .catch(e => {
            console.log('error : ', e)
          })

      },
      delReply(index, noticeId) {
        axios.delete('http://localhost:8080/replies/' + index)
          .then(response => {

            axios.get('http://localhost:8080/replies/notice/' + noticeId)
              .then(response => {
                this.replies = response.data.data;
              })
              .catch(e => {
                console.log('error : ', e)
              });

          })
          .catch(e => {
            console.log('error : ', e)
          });
      },
      paginationNotice(pageValue) {
        console.log("pageValue", pageValue);
        axios.get('http://localhost:8080/notices',
          {
            params: {
              page: pageValue
            }
          })
          .then(response => {
            this.notices = response.data.data.content;
            this.startPage = (Math.ceil((response.data.data.pageable.pageNumber + 1) / 10.0) * 10) - 9;
            this.currentPage = response.data.data.pageable.pageNumber + 1;
            this.totalPage = response.data.data.totalPages;

            console.log("this.startPage - 시작페이지 - ", this.startPage);
            console.log("this.currentPage - 현재페이지 - ", this.currentPage);
            console.log("this.totalPage - 총페이지 - ", this.totalPage);
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
    // ,
    // filters: {
    //   formatDate: function (value) {
    //     if (value) {
    //       return moment(String(value)).format('YYYY-MM-DD')
    //     }
    //   }
    // }

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
