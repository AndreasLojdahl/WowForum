<template>
  <div class="card text-center container mb-4" @click="goTo()">
    <div class="card-header row">
      <h2 class="col text-left">{{ threadObj.title }}</h2>
      <div class="col justify-content-end text-right">
        <div class="col d-flex justify-content-end">
          <div v-if="threadObj.threadOwner ? threadObj.threadOwner : false">
            {{ threadObj.threadOwner.username }}
          </div>
          <div v-if="!threadObj.threadOwner">removed user</div>
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="20"
            height="20"
            fill="currentColor"
            class="bi bi-person-circle ml-3"
            viewBox="0 0 16 16"
          >
            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
            <path
              fill-rule="evenodd"
              d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"
            />
          </svg>
        </div>
        <div class="col item-font">{{ getDate }}</div>
      </div>
    </div>
    <div
      class="card-footer text-muted d-flex justify-content-start"
      v-if="isAdmin || isModerator"
    >
      <svg
        v-if="isAdmin"
        xmlns="http://www.w3.org/2000/svg"
        width="25"
        height="25"
        fill="currentColor"
        class="bi bi-trash trash svg-icon mr-2"
        viewBox="0 0 16 16"
        @click.stop="deleteThread()"
      >
        <path
          d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"
        />
        <path
          fill-rule="evenodd"
          d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4L4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"
        />
      </svg>
      <div v-if="isAdmin" class="mr-4">Ta bort tråd</div>

      <svg
        v-if="isAdmin || isModerator"
        xmlns="http://www.w3.org/2000/svg"
        width="25"
        height="25"
        fill="currentColor"
        :class="{ isLocked: threadObj.locked, isOpen: !threadObj.locked }"
        class="bi bi-file-lock2 svg-icon mr-2"
        viewBox="0 0 16 16"
        @click.stop="lockThread()"
      >
        <path
          d="M8 5a1 1 0 0 1 1 1v1H7V6a1 1 0 0 1 1-1zm2 2.076V6a2 2 0 1 0-4 0v1.076c-.54.166-1 .597-1 1.224v2.4c0 .816.781 1.3 1.5 1.3h3c.719 0 1.5-.484 1.5-1.3V8.3c0-.627-.46-1.058-1-1.224z"
        />
        <path
          d="M4 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H4zm0 1h8a1 1 0 0 1 1 1v12a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1z"
        />
      </svg>
      <div class="">Lås tråd</div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    threadObj: Object,
    forum_id: Number,
  },
  computed: {
    getDate() {
      return new Date(this.threadObj.timestamp).toLocaleString("sv-SE");
    },
    user() {
      return this.$store.state.loggedInUser;
    },
    thread() {
      return this.threadObj;
    },
    isModerator() {
      return this.user?.forumsToModerate.includes(this.forum_id);
    },
    isAdmin() {
      return this.user?.roles.includes("ADMIN");
    },
  },
  methods: {
    goTo() {
      this.$store.commit("setThread", this.threadObj);
      this.$router.push({
        path: `${this.forum_id}/thread/${this.threadObj.thread_id}`,
      });
      // v-if="user ? user.roles.includes('ADMIN'): false"
    },
    async deleteThread() {
      const response = await fetch(
        `/api/v1/forums/threads/${this.threadObj.thread_id}`,
        {
          method: "DELETE",
        }
      );
      if (response.status === 204) {
        this.$store.dispatch("fetchForum", this.forum_id);
      }
    },
    async lockThread() {
      let thread = {
        // title: this.threadObj.title,
        // forum_id: this.threadObj.forum_id,
        // timestamp: this.threadObj.timestamp,
        // threadOwner: this.threadObj.threadOwner,
        locked: !this.threadObj.locked,
        // messages: this.threadObj.messages,
        // user: this.threadObj.user,
      };
      let response = await fetch(
        `/api/v1/forums/threads/${this.threadObj.thread_id}`,
        {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(thread),
        }
      );
      if (response.status === 204) {
        this.$store.dispatch("fetchForum", this.forum_id);
      }
    },
  },
};
</script>
<style scoped>
.item-font {
  font-size: 1rem;
  font-style: oblique;
}
.item:hover {
  cursor: pointer;
}
.isLocked {
  color: red;
}
.isOpen {
  color: green;
}

.svg-icon:hover {
  cursor: pointer;
  color: red;
}
.svg-icon.isLocked:hover {
  cursor: pointer;
  color: green;
}

.card:hover {
  cursor: pointer;
}
</style>
