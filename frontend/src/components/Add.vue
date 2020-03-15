<template>
  <div id="Add">
    <form @submit="addExecute()" enctype="multipart/form-data">
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
                          <button type="button" class="btn btn-info btn-sm">중복확인</button>
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
                  <input v-model="form.uploadFile01" name="uploadFile01" type="file" id="iptFile01" accept="" onchange="javascript: document.getElementById('iptFileName01').value=this.files[0].name"/>
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
          uploadFile01: ''
        }
      }
    },
    methods: {
      addExecute(e) {
        e.preventDefault();
        //
        // const formData = new FormData();
        //
        // console.log("title", this.form.title);
        // console.log("contents", this.form.contents);
        // console.log("writer", this.form.writer);
        // console.log("uploadFile01", this.form.uploadFile01);


        // formData.append("title", this.form.title);
        // console.log("formData", formData);
        // formData.append("contents", this.form.contents);
        // console.log("formData", formData);
        // formData.append("writer", this.form.writer);
        // console.log("formData", formData);
        // formData.append("uploadFile01", this.form.uploadFile01);
        // console.log("formData", formData);

        // axios({
        //   method: 'post',
        //   baseURL: 'http://localhost:8080/notices',
        //   data: formData,
        //   headers: {'Content-Type': 'multipart/form-data'}
        // })
        // //   axios.post('http://localhost:8080/notices', formData, {
        // //   header:{
        // //     'Content-Type' : 'multipart/form-data'
        // //   }
        // // })
        //   .then(response => {
        //     console.log(response);
        //   })
        //   .catch(e => {
        //     console.log('error : ', e)
        //   });
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
