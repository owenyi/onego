<template>
  <v-row justify="space-around">
    <v-col
      cols="12"
      sm="10"
      md="8"
    >
      <v-sheet
        elevation="10"
        class="py-4 px-1"
      >
        <v-chip-group
          mandatory
          active-class="primary--text"
        >
          <v-chip
            v-for="tag in tags"
            :key="tag"
          >
            {{ tag }}
          </v-chip>
        </v-chip-group>
      </v-sheet>
    </v-col>
  </v-row>
</template>

<script>
  export default {
    data: () => ({
      tags: [
        '일상',
        '시사 이슈',
        'IT 트렌드',
        '요리',
        '운동',
        '사랑',
        '이별'
      ],
    }),
  }
</script>

<style>

</style>