<template>
  <div id="Edit">
    <div>
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
                <input name="title" type="text" class="form-control" required="required" placeholder="제목을 입력해 주세요."/>
              </div>
            </td>
          </tr>
          <tr>
            <th class="text-center">내용</th>
            <td>
              <textarea name="contents" type="textarea" row="5" style="resize: none; overflow: auto;" class="form-control" placeholder="내용을 입력해 주세요."></textarea>
            </td>
          </tr>

          <tr>
            <th class="text-center">작성자 이름</th>
            <td>
              <input name="writer" type="text" class="form-control" required="required" placeholder="작성자 이름을 입력해 주세요."/>
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
                  <input name="uploadFile01" type="file" id="iptFile01" accept="" onchange="javascript: document.getElementById('iptFileName01').value=this.files[0].name"/>
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
          <button type="button" class="btn btn-info btn-sm">등록</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'Home',
    data() {
      return {

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
