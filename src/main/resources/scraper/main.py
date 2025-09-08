from ekSkemaScraper import scrapeTo as scrape_kea
import requests
import sys
import io
sys.stdout = io.TextIOWrapper(sys.stdout.detach(), encoding='utf-8')

def send_to_backend(data):
    try:
        response = requests.post(
            "http://localhost:8080/api/scraped",
            json=data,
            headers={'Content-Type': 'application/json'}
        )
        print(f"✅ Sent to backend. Status code: {response.status_code}", file=sys.stderr)
    except Exception as e:
        print(f"❌ Error sending to backend: {e}", file=sys.stderr)

if __name__ == "__main__":
    all_jobs = []

    print("\n=== Scraping KEA ===", file=sys.stderr)
    try:
        all_jobs += scrape_kea()
    except Exception as e:
        print(f"⚠️ KEA scraper failed: {e}", file=sys.stderr)

    if all_jobs:
        print(f"🎉 Total scraped: {len(all_jobs)} jobs", file=sys.stderr)
        # Kun JSON printes til stdout
        print(all_jobs := all_jobs)  # dette kan bruges til Spring Boot, men bedre: json.dumps(all_jobs)
        import json
        print(json.dumps(all_jobs))
        send_to_backend(all_jobs)
    else:
        print("🚫 No jobs scraped.", file=sys.stderr)
