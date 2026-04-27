# 🔐 GitHub SSH Setup (Clean Setup Step-by-Step)

This guide shows how to remove old SSH keys and configure a new SSH connection for GitHub.

---

## ✅ Step 1: Remove Old SSH Keys

```bash
cd ~/.ssh
ls
rm id_ed25519 id_ed25519.pub
```

(Optional)

```bash
rm known_hosts
```

---

## ✅ Step 2: Generate New SSH Key

```bash
ssh-keygen -t ed25519 -C "your-email@example.com"
```

* Press **Enter** for default location
* Press **Enter** to skip passphrase (optional)

---

## ✅ Step 3: Start SSH Agent

```bash
eval "$(ssh-agent -s)"
```

---

## ✅ Step 4: Add SSH Key to Agent

```bash
ssh-add ~/.ssh/id_ed25519
```

---

## ✅ Step 5: Copy Public Key

```bash
cat ~/.ssh/id_ed25519.pub
```

* Copy the full output (starts with `ssh-ed25519`)

---

## ✅ Step 6: Add SSH Key to GitHub

1. Go to GitHub → Settings
2. Click **SSH and GPG keys**
3. Click **New SSH key**

Fill:

* **Title:** My Laptop
* **Key:** Paste copied key

Click **Add SSH key**

---

## ✅ Step 7: Test SSH Connection

```bash
ssh -T git@github.com
```

If prompted:

```bash
yes
```

Expected output:

```
Hi USERNAME! You've successfully authenticated, but GitHub does not provide shell access.
```

---

## ✅ Step 8: Use SSH in Your Project

Go to your project:

```bash
cd ~/your-project-folder
git remote -v
```

If it shows HTTPS:

```bash
https://github.com/username/repo.git
```

Change to SSH:

```bash
git remote set-url origin git@github.com:username/repo.git
```

---

## 🚀 Test Push

```bash
git push
```

✅ No username or password required

---

## 🎯 Final Result

* SSH configured correctly
* Secure connection to GitHub
* No password/token needed
* Ready for development

---
