<template>
  <div id="Add">
    <form method="post" v-on:submit.prevent="addExecute" enctype="multipart/form-data">
<!--    <form @submit="addExecute()" enctype="multipart/form-data">-->
      <div>
        <table class="table table-bordered m-0">
          <colgroup>
            <col width="30%"/>
            <col width="*%"/>
          </colgroup>
          <tbody>
          <tr>
            <th class="text-center">제목</th>
            <td>
              <div class="input-group">
                <input v-model="form.title" name="title" type="text" class="form-control" required="required" placeholder="제목을 입력해 주세요."/>
                <span class="input-group-btn">
                  <button type="button" @click="existsTitle()" class="btn btn-info btn-sm">중복확인</button>
                </span>
              </div>
            </td>
          </tr>
          <tr>
            <th class="text-center">내용</th>
            <td>
              <textarea v-model="form.contents" name="contents" type="textarea" row="5" style="resize: none; overflow: auto;" class="form-control" placeholder="내용을 입력해 주세요."></textarea>
            </td>
          </tr>

          <tr>
            <th class="text-center">작성자 이름</th>
            <td>
              <input v-model="form.writer" name="writer" type="text" class="form-control" required="required" placeholder="작성자 이름을 입력해 주세요."/>
            </td>
          </tr>

          <tr>
            <th class="text-center">첨부파일</th>
            <td>
              <div class="form-group m-0">
                <div class="input-group m-0">
                  <input type="text" class="form-control" id="iptFileName01" placeholder="우측 버튼을 통해 첨부파일을 선택해주세요." readonly/>
                  <div class="input-group-btn">
                    <button type="button" class="btn btn-info btn-sm" id="btnFile01" onclick="document.getElementById('iptFile01').click();">파일첨부</button>
                  </div>
                </div>
                <div hidden>
                  <input ref="file" name="uploadFile01" type="file" id="iptFile01" accept="" onchange="javascript: document.getElementById('iptFileName01').value=this.files[0].name"/>
                </div>
              </div>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
      <div class="clearfix"></div>
      <div class="ln_solid"></div>

      <div class="col-xs-12">
        <div class="text-center">
          <button type="submit" class="btn btn-info btn-sm">등록</button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
  export default {
    name: 'Add',

    data() {
      return {
        form: {
          title: '',
          contents: '',
          writer: '',
          file: ''
        }
      }
    },
    methods: {
      existsTitle(){
        var title = this.form.title;

        axios.get('http://localhost:8080/notices/title',
          {
            params: {title: title}
          })
          .then(response => {
            if (!response.data.data) {
              alert(response.data.data);
            }

          })
          .catch(e => {
            console.log('error : ', e)
          });
      },
      addExecute() {
        const formData = new FormData();
        let fileData = this.$refs.file.files[0];

        if(fileData === undefined){fileData = null}

        formData.append("title", this.form.title);
        formData.append("contents", this.form.contents);
        formData.append("writer", this.form.writer);
        formData.append("uploadFile01", fileData);

        console.log("title - ", this.form.title);
        console.log("contents - ", this.form.contents);
        console.log("writer - ", this.form.writer);
        console.log("file - ", fileData);

        axios.post('http://localhost:8080/notices', formData,
          {
          header: {
            'Content-Type': 'multipart/form-data'
          }
          // params: {
          //   uploadFile01: fileData
          // }
        })
          .then(response => {
            alert("등록 성공!");
            this.$router.push({name: 'home'})
          })
          .catch(e => {
            console.log('error : ', e)
          });
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
