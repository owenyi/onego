<template>
  <v-card
    max-width="95%"
    flat
    class="mx-auto"
  >

    <v-list three-line min-height="250">
      <template>
        <v-list-item class="comment" v-for="item in items" :key="item.title">
          <v-list-item-avatar size="55">
            <v-img :src="item.avatar"></v-img>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title 
              style="font-family: Noto Sans KR; font-size : 1.25rem; font-weight: 400; padding-top:10px;" 
              v-html="item.title">
            </v-list-item-title>
            
            <v-btn 
            absolute
            depressed
            right
            small
            class="comment-btn"
            >
              수정</v-btn>

            <v-btn 
            absolute
            small
            depressed
            right
            class="comment-btn"
            >
              삭제</v-btn>
              
            <v-list-item-subtitle 
              style="font-family: Noto Sans KR; font-size : 1rem; font-weight: 300; padding-bottom:15px;"  
              v-html="item.subtitle">
            </v-list-item-subtitle>
            
            
          </v-list-item-content>
          <br/><br/><br/>
        </v-list-item>
      </template>
    </v-list>
    <v-textarea
        class="mx-2"
        prepend-icon=""
        outlined
        style="width: 90%; margin-left: 100;"
        height="80px"
        value="댓글을 입력해주세요!"
    ></v-textarea>
  </v-card>
</template>


<script>
export default {
     data: () => ({
      items: [
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
          title: 'Brunch this weekend?',
          subtitle: `I'll be in your neighborhood doing errands this weekend. Do you want to hang out?`,
        },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
          title: 'Summer BBQ',
          subtitle: `Wish I could come, but I'm out of town this weekend.`,
        }
      ]
    }),
}
</script>

<style>

.comment{
   height: 100px;
}

</style>