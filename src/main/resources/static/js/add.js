console.log("add 들어왔다");

var Add = Vue.component('add', {
    template: '<div>\n' +
        '              <div>\n' +
        '                <table class="table table-bordered m-0">\n' +
        '                  <colgroup>\n' +
        '                    <col width="30%"/>\n' +
        '                    <col width="*%"/>\n' +
        '                  </colgroup>\n' +
        '                  <tbody>\n' +
        '                  <tr>\n' +
        '                    <th class="text-center">제목</th>\n' +
        '                    <td>\n' +
        '                      <div class="input-group">\n' +
        '                        <input name="title" type="text" class="form-control" required="required" placeholder="제목을 입력해 주세요."/>\n' +
        '                        <span class="input-group-btn">\n' +
        '                          <button type="button" class="btn btn-info btn-sm">중복확인</button>\n' +
        '                        </span>\n' +
        '                      </div>\n' +
        '                    </td>\n' +
        '                  </tr>\n' +
        '                  <tr>\n' +
        '                    <th class="text-center">내용</th>\n' +
        '                    <td>\n' +
        '                      <textarea name="contents" type="textarea" row="5" style="resize: none; overflow: auto;" class="form-control" placeholder="내용을 입력해 주세요."></textarea>\n' +
        '                    </td>\n' +
        '                  </tr>\n' +
        '\n' +
        '                  <tr>\n' +
        '                    <th class="text-center">작성자 이름</th>\n' +
        '                    <td>\n' +
        '                      <input name="writer" type="text" class="form-control" required="required" placeholder="작성자 이름을 입력해 주세요."/>\n' +
        '                    </td>\n' +
        '                  </tr>\n' +
        '\n' +
        '                  <tr>\n' +
        '                    <th class="text-center">첨부파일</th>\n' +
        '                    <td>\n' +
        '                      <div class="form-group m-0">\n' +
        '                        <div class="input-group m-0">\n' +
        '                          <input type="text" class="form-control" id="iptFileName01" placeholder="우측 버튼을 통해 첨부파일을 선택해주세요." readonly/>\n' +
        '                          <div class="input-group-btn">\n' +
        '                            <button type="button" class="btn btn-info btn-sm" id="btnFile01" onclick="document.getElementById(\'iptFile01\').click();">파일첨부</button>\n' +
        '                          </div>\n' +
        '                        </div>\n' +
        '                        <div hidden>\n' +
        '                          <input name="uploadFile01" type="file" id="iptFile01" accept="" onchange="javascript: document.getElementById(\'iptFileName01\').value=this.files[0].name"/>\n' +
        '                        </div>\n' +
        '                      </div>\n' +
        '                    </td>\n' +
        '                  </tr>\n' +
        '                  </tbody>\n' +
        '                </table>\n' +
        '              </div>\n' +
        '              <div class="clearfix"></div>\n' +
        '              <div class="ln_solid"></div>\n' +
        '\n' +
        '              <div class="col-xs-12">\n' +
        '                <div class="text-center">\n' +
        '                  <button type="button" class="btn btn-info btn-sm">등록</button>\n' +
        '                </div>\n' +
        '              </div>\n' +
        '            </div>'

});