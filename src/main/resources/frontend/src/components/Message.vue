<template>
  <div class="card text-center container mt-4">
    <div class="card-header row">
      <div class="row">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="20"
          height="20"
          fill="currentColor"
          class="bi bi-person-circle ml-3 "
          viewBox="0 0 16 16"
        >
          <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
          <path
            fill-rule="evenodd"
            d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"
          />
        </svg>
        <div
          class="col"
          v-if="message.messageOwner ? message.messageOwner : false"
        >
          {{ message.messageOwner.username }}
        </div>
        <div class="col" v-if="!message.messageOwner">removed user </div>
      </div>
      <div class="col text-right">{{ getDate }} aaa {{this.$route.params.forum_id }} aaa</div>
    </div>
    <div class="card-body">
      <p
        class="card-text text-left msg-content"
        :class="{ isWarning: message.warningPost }"
      >
        {{ message.messageContent }}
      </p>
    </div>
    <div
      class="card-footer text-muted d-flex justify-content-start"
      v-if="isAdmin || isModerator"
    >
      <svg
        xmlns="http://www.w3.org/2000/svg"
        width="25"
        height="25"
        fill="currentColor"
        class="bi bi-trash trash mr-2 trash-icon"
        viewBox="0 0 16 16"
        @click="deleteMessage()"
      >
        <path
          d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"
        />
        <path
          fill-rule="evenodd"
          d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"
        />
      </svg>
      <div>Ta bort inlägg</div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    message: Object,
  },
  computed: {
    getDate() {
      return new Date(this.message.timestamp).toLocaleString("sv-SE");
    },
    user() {
      return this.$store.state.loggedInUser;
    },
    isAdmin() {
      return this.user?.roles.includes("ADMIN");
    },
    isModerator() {
      return this.user?.forumsToModerate.includes(Number(this.$route.params.forum_id));
    }
  },
  methods: {
    async deleteMessage() {
      const response = await fetch(
        `/api/v1/messages/${this.message.message_id}`,
        {
          method: "DELETE",
        }
      );
      console.log(response);
      if (response.status === 204) {
        await this.$store.dispatch("fetchThread", this.$route.params.thread_id);
      }
    },
  },
};
</script>
<style scoped>
.msg-font {
  font-size: 0.8rem;
  font-style: oblique;
}

.msg-content {
  font-weight: bold;
}

.isWarning {
  color: red;
}

.trash-icon:hover {
  color: red;
  cursor: pointer;
}
</style>
