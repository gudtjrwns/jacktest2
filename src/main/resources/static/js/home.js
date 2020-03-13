console.log("home 들어왔다");

var Home = Vue.component('home', {
    data: function () {
        return {
            notices: [

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
    template: '<div><div>\n' +
        '              <div class="pull-left">\n' +
        '                <div class="form-inline">\n' +
        '                  <div class="form-group">\n' +
        '<router-link to="/add">'+
        '                    <button type="button" class="btn btn-sm btn-info">\n' +
        '                      <i class="far fa-edit"></i> 신규등록\n' +
        '                    </button>\n' +
        '</router-link>'+
        '                  </div>\n' +
        '                  <div class="form-group">\n' +
        '                    <button type="button" class="btn btn-sm btn-danger">\n' +
        '                      <i class="far fa-edit"></i> 삭제\n' +
        '                    </button>\n' +
        '                  </div>\n' +
        '                </div>\n' +
        '              </div>\n' +
        '\n' +
        '              <div class="pull-right">\n' +
        '                <div class="form-group">\n' +
        '                  <div class="input-group">\n' +
        '                    <input type="text" class="form-control" placeholder="검색어를 입력해주세요."/>\n' +
        '                    <span class="input-group-btn">\n' +
        '                      <button type="button" class="btn btn-sm btn-default">검색</button>\n' +
        '                    </span>\n' +
        '                  </div>\n' +
        '                </div>\n' +
        '              </div>\n' +
        '            </div>\n' +
        '\n' +
        '            <div>\n' +
        '              <table class="table table-striped table-bordered table-hover">\n' +
        '                <colgroup>\n' +
        '                  <col width="8%" />\n' +
        '                  <col width="*%" />\n' +
        '                  <col width="15%" />\n' +
        '                  <col width="15%" />\n' +
        '                  <col width="8%" />\n' +
        '                  <col width="8%" />\n' +
        '                </colgroup>\n' +
        '                <thead class="thead-dark">\n' +
        '                <tr>\n' +
        '                  <th class="text-center">No</th>\n' +
        '                  <th class="text-center">제목</th>\n' +
        '                  <th class="text-center">작성자</th>\n' +
        '                  <th class="text-center">등록일</th>\n' +
        '                  <th class="text-center">수정</th>\n' +
        '                  <th class="text-center">삭제</th>\n' +
        '                </tr>\n' +
        '                </thead>\n' +
        '                <tbody>\n' +
        '                <tr v-for="notice in notices">\n' +
        '                  <td class="text-center">{{notice.id}}</td>\n' +
        '                  <td>{{notice.title}}</td>\n' +
        '                  <td class="text-center">{{notice.writer}}</td>\n' +
        '                  <td class="text-center">{{notice.credate}}</td>\n' +
        '                  <td class="text-center"><router-link to="/edit">수정</router-link></td>\n' +
        '                  <td class="text-center">삭제</td>\n' +
        '                </tr>\n' +
        '                </tbody>\n' +
        '              </table>\n' +
        '            </div></div>'


});