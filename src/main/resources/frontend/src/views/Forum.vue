<template>
  <div class="container" v-if="forum">
    <div class="row justify-content-center mt-5">
      <h1  class="forum-header">{{ forum.name }}</h1>
    </div>

    <form v-if="user" @submit.prevent="createThread" class="mb-4">
      <div class="form-group">
        <label for="title">Titel</label>
        <input
          type="text"
          class="form-control"
          id="title"
          required
          v-model="thread.title"
        />
      </div>
      <div class="form-group">
        <label for="message">Meddelande</label>
        <input
          type="text"
          class="form-control"
          id="message"
          required
          v-model="thread.initialMessage"
        />
      </div>
      <button type="submit" class="btn btn-dark">
        Skapa ny tråd
      </button>
    </form>
    <div class="list-item-container">

    <ForumListItem
      
      v-for="thread in forum.threads"
      :key="thread.thread_id"
      :threadObj="thread"
      :forum_id="forum.forum_id"
    />
    
    </div>

  </div>
</template>

<script>
import ForumListItem from "../components/ForumListItem.vue";
// @ is an alias to /src

export default {
  name: "Forum",
  props: {},
  components: {
    ForumListItem,
  },
  data() {
    return {
      thread: {
        title: null,
        initialMessage: null,
      },
    };
  },
  computed: {
    paramId() {
      return this.$route.params.forum_id;
    },
    forum() {
      return this.$store.state.forum;
    },
    user(){
      return this.$store.state.loggedInUser;
    }
  },
  methods: {
    async createThread() {
      let threadToCreate = {
        title: this.thread.title,
        initialMessage: this.thread.initialMessage,
        user: this.user.user_id
      };
      let newThread = await fetch(
        `/api/v1/forums/${this.forum.forum_id}/threads`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(threadToCreate),
        }
      );
      newThread = await newThread.json();
      this.$store.commit("addNewThread", newThread)
      this.thread.title = null
      this.thread.initialMessage = null
    },
  },
  watch: {
    paramId: function() {
      this.$store.dispatch("fetchForum", this.paramId);
    },
  },
  created() {
    this.$store.dispatch("fetchForum", this.paramId);
  },
};
</script>
<style scoped>
.list-item-container{
  margin-bottom: 5em;
}
</style>>
