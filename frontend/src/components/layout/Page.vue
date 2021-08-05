<template>
  <div id="item">
    <v-flex
        v-for="article in articles"
        v-bind:key="article.title"
    >
      <v-card
        class="mx-auto"
        max-width="55%"
        max-height="600"
        tile=false
      >
        <div class="card">
          <v-card-text class="text">
            <div style="font-size : 1.45rem;" v-html="article.title"></div>
            <br/>
            <div class="text-post" v-html="article.text"></div>
          </v-card-text>

          <v-card-actions class="avatar-box">
              <v-list-item-avatar color="grey darken-3">
                <v-img
                  class="avatar"
                  alt="avatar"
                  src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
                ></v-img>
              </v-list-item-avatar>
              <v-list-item-content class="author-date">
                <span class="author" v-html="article.author"></span>
                <span class="date" v-html="article.date"></span>
              </v-list-item-content>
          </v-card-actions>
          <br/>
        </div>
      </v-card>
    </v-flex>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
export default Vue.extend({
      data: () => (
        model
      ),
    })
var model = {
	article:'',
    articles: [
      { title: "진짜 내 사람 구별하는 방법",
        text: "얕은 만남이 판치는 시대에서 '내 사람'을 구별하는 방법  |  우리 아빠에겐 친구가 몇 명없다. 나는 아빠의 친구분들과는 별로 안면이 없지만 유일하게 기억에 남는 딱 한분이 계신다. 그분을 처음 본건 친 할머니의 장례식장에서 였다. 서울에서 4시간가량 떨어진 먼 장례식장에도 제일 먼저 달려와 준 그 사람. 장례식장에서 계속 울고불고 하던 아빠를 잠깐이라도 웃음짓게 만들어준 그 사람을 보며 '진짜 내 사람'의 정석을 배웠다. '진짜 내 사람'은 아무 말을 하지 않아도 표정으로 마음을 읽는다. 장례식장에서 점점 언성이 높아지고, 헛소리를 하는 아빠를 보며 우리 가족들은 '너무 취했다.'라고 말했지만 유일하게 그 분은 '지금은 진짜 취한 게 아니에요.'라고 말하며 아빠에 대해 정확하게 파악하고 있었다.",
        author: "브런치 작가",
        date: "2.15.2020"
      },
      { title: "Lorem ipsum2",
        text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        author: "Jonathan",
        date: "2.15.2020"
      },
      { title: "Lorem ipsum3",
        text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        author: "Jonathan",
        date: "2.15.2020"
      },
      { title: "Lorem ipsum4",
        text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        author: "Jonathan",
        date: "2.15.2020"
      },
      { title: "Lorem ipsum5",
        text: "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        author: "Jonathan",
        date: "2.15.2020"
      }
    ]
}
</script>

<style>
  #item {
		font-family: "Noto Sans KR", sans-serif !important;
	}
  .card{
    margin: 0px 15px 0px 15px;
  }
  .text{
    padding-top: 30px;
    padding-bottom: 5px;
  }
  .text-post{
    font-size : 1.1rem;
    line-height: 1.7em;
    font-weight: 300;
    letter-spacing: 0;
    /* 글자수 제한*/
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    word-wrap:break-word;
    height: 5.1em; /*height는 1.7em * 3줄 = 5.1em */
  }
  .avatar-box{
    padding-left: 17px;
  }
</style>